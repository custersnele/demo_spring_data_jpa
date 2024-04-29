package be.pxl.builder;

import be.pxl.domain.Author;
import be.pxl.domain.Book;

import java.util.Set;

public final class AuthorBuilder {
    private String name;
    private Set<Book> books;

    private AuthorBuilder() {
    }

    public static AuthorBuilder anAuthor() {
        return new AuthorBuilder();
    }

    public AuthorBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AuthorBuilder withBooks(Set<Book> books) {
        this.books = books;
        return this;
    }

    public Author build() {
        Author author = new Author();
        author.setName(name);
        author.setBooks(books);
        return author;
    }
}
