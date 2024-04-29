package be.pxl.repository;

import be.pxl.builder.AuthorBuilder;
import be.pxl.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AuthorRepositoryTest {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private AuthorRepository authorRepository;

	private final Author author1 = AuthorBuilder.anAuthor().withName("Famous Author").build();


	private final Author author2 = AuthorBuilder.anAuthor().withName("Not So Famous Author").build();

	@BeforeEach
	public void init() {
		authorRepository.saveAll(Arrays.asList(author1, author2));
		entityManager.flush();
		entityManager.clear();
	}

	@Test
	public void returnsAuthorByName() {
		Optional<Author> author = authorRepository.findAuthorByName("Famous Author");

		assertTrue(author.isPresent());
		assertEquals("Famous Author", author.get().getName());
	}

	@Test
	public void returnsEmptyOptionalWhenNoFootballPlayerWithEmail() {
		Optional<Author> author = authorRepository.findAuthorByName("Bestseller Author");

		assertTrue(author.isEmpty());
	}
}
