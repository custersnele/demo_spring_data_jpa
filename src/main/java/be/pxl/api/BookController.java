package be.pxl.api;

import be.pxl.api.data.BookDto;
import be.pxl.api.data.FilterDto;
import be.pxl.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("byAuthor")
    public List<String> findBooksByAuthor(@RequestBody FilterDto filter) {
        return bookService.getBooksByAuthor(filter.authorName());
    }

    @GetMapping("search")
    public List<BookDto> searchBooks(@RequestBody FilterDto filter) {
        return bookService.searchBooks(filter);
    }

    @GetMapping
    public Page<BookDto> getBooks(Pageable pageable) {
        return bookService.findAllBooks(pageable);
    }
}
