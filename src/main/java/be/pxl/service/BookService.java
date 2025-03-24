package be.pxl.service;

import be.pxl.api.data.BookDto;
import be.pxl.api.data.FilterDto;
import be.pxl.domain.Author;
import be.pxl.domain.Book;
import be.pxl.exception.NotFoundException;
import be.pxl.repository.AuthorRepository;
import be.pxl.repository.BookRepository;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private static final Logger LOGGER = LogManager.getLogger(BookService.class);

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<String> getBooksByAuthor(String authorName) {
        LOGGER.info("Starting getBooksByAuthor");
        Author author = authorRepository.findAuthorByName(authorName).orElseThrow(() -> new NotFoundException(""));
        LOGGER.info("Author retrieved...");
        return author.getBooks().stream().map(Book::getTitle).toList();
    }

    @Transactional
    public Page<BookDto> findAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(this::mapToBookDto);
    }

    @Transactional
    public void doTimeConsumingTask() {
        System.out.println("Waiting for a time-consuming task that doesn't need a database connection ...");
        try {
            Thread.sleep(40000);
            System.out.println("Done, now query the database ...");
            System.out.println("The database connection should be acquired now ...");
            Book book = bookRepository.findById(1L).orElse(null);
            // at this point, the connection should be open
            Thread.sleep(40000);
            if (book != null) {
                book.setTitle(LocalDateTime.now().toString());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<BookDto> searchBooks(FilterDto filter) {
        return bookRepository.findAll(createBookSpecification(filter))
                .stream()
                .map(this::mapToBookDto)
                .toList();
    }

    private Specification<Book> createBookSpecification(FilterDto filter) {
        return new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> allPredicates = new ArrayList<>();
                if (filter.titlePart() != null) {
                    allPredicates.add(builder.like(builder.lower(root.get("title")), "%" + filter.titlePart().toLowerCase() + "%"));
                }
                if (filter.category() != null) {
                    allPredicates.add(builder.equal(root.get("category"), filter.category()));
                }
                // Search by author name
                if (filter.authorName() != null) {
                    // Join book to authors
                    Join<Book, Author> authors = root.join("authors", JoinType.LEFT);
                    // Add predicate for author name
                    allPredicates.add(builder.like(builder.lower(authors.get("name")), "%" + filter.authorName().toLowerCase() + "%"));
                }

                // Ensure distinct results since joins might produce duplicate rows
                query.distinct(true);

                return builder.and(allPredicates.toArray(new Predicate[0]));
            }
        };
    }

    private BookDto mapToBookDto(Book book) {
        return new BookDto(book.getTitle(), book.getCategory(), book.getAuthors().stream().map(Author::getName).toList());
    }
}
