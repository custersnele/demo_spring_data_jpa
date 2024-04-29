package be.pxl.builder;

import be.pxl.domain.Author;
import be.pxl.domain.Book;

import java.util.Set;

public final class BookBuilder {
    private String title;
    private Set<Author> authors;

    private BookBuilder() {
    }

    public static BookBuilder aBook() {
        return new BookBuilder();
    }

    public BookBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder withAuthors(Set<Author> authors) {
        this.authors = authors;
        return this;
    }

    public Book build() {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(authors);
        return book;
    }
}
