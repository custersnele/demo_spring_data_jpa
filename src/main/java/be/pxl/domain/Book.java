package be.pxl.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(value = EnumType.STRING)
    private BookCategory category;

    public String getTitle() {
        return title;
    }

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author) {
        authors.add(author);
        author.addBook(this);
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public Set<Author> getAuthors() {
        return authors;
    }
}
