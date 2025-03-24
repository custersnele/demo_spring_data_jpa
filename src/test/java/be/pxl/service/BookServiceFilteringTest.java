package be.pxl.service;

import be.pxl.api.data.BookDto;
import be.pxl.api.data.FilterDto;
import be.pxl.builder.AuthorBuilder;
import be.pxl.builder.BookBuilder;
import be.pxl.domain.Author;
import be.pxl.domain.Book;
import be.pxl.domain.BookCategory;
import be.pxl.repository.AuthorRepository;
import be.pxl.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookServiceFilteringTest {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	private BookService bookService;

	@BeforeEach
	public void setup() {
		bookService = new BookService(authorRepository, bookRepository);

		Author author1 = AuthorBuilder.anAuthor().withName("Tessa Fairwind").build();
		Author author2 = AuthorBuilder.anAuthor().withName("Quentin Ashlore").build();

		authorRepository.saveAll(List.of(author1, author2));

		Book book1 = BookBuilder.aBook()
				.withTitle("The Crown of Thorns")
				.withCategory(BookCategory.HISTORY)
				.withAuthors(Set.of(author2))
				.build();

		Book book2 = BookBuilder.aBook()
				.withTitle("Whispers of the Ancient")
				.withCategory(BookCategory.HISTORY)
				.withAuthors(Set.of(author1))
				.build();

		Book book3 = BookBuilder.aBook()
				.withTitle("The Romantic Escape")
				.withCategory(BookCategory.NON_FICTION)
				.withAuthors(Set.of(author1))
				.build();

		bookRepository.saveAll(List.of(book1, book2, book3));
	}

	@Test
	@Transactional
	public void filterBooksByCategoryAndTitleAndAuthor() {
		FilterDto filter = new FilterDto("Tessa", BookCategory.HISTORY, "Ancient");

		List<BookDto> result = bookService.searchBooks(filter);

		assertEquals(1, result.size());
		BookDto book = result.get(0);
		assertEquals("Whispers of the Ancient", book.title());
		assertTrue(book.authors().contains("Tessa Fairwind"));
		assertEquals(BookCategory.HISTORY, book.category());
	}

	@Test
	@Transactional
	public void filterBooksByAuthorOnly() {
		FilterDto filter = new FilterDto("Quentin", null, null);

		List<BookDto> result = bookService.searchBooks(filter);

		assertEquals(1, result.size());
		assertEquals("The Crown of Thorns", result.get(0).title());
	}

	@Test
	@Transactional
	public void filterBooksWithNoMatchReturnsEmptyList() {
		FilterDto filter = new FilterDto("Unknown Author", null, null);

		List<BookDto> result = bookService.searchBooks(filter);

		assertTrue(result.isEmpty());
	}
}
