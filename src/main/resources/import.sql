

INSERT INTO authors (name) VALUES ('Elara Thornwood');
INSERT INTO authors (name) VALUES ('Milo Ventris');
INSERT INTO authors (name) VALUES ('Tessa Fairwind');
INSERT INTO authors (name) VALUES ('Quentin Ashlore');
INSERT INTO authors (name) VALUES ('Nora Spellbound');
INSERT INTO authors (name) VALUES ('Cyrus Drakewind');

INSERT INTO books (title, category) VALUES ('Whispers of the Ancient', 'HISTORY');
INSERT INTO books (title, category) VALUES ('The Last Ember', 'HISTORY');
INSERT INTO books (title, category) VALUES ('Beneath the Starless Sky', 'SCIENCE');
INSERT INTO books (title, category) VALUES ('Echoes of the Forgotten', 'HISTORY');
INSERT INTO books (title, category) VALUES ('The Glass Fortress', 'NON-FICTION');
INSERT INTO books (title, category) VALUES ('The Chronosmith Chronicles', 'SCIENCE');
INSERT INTO books (title, category) VALUES ('River of Shadows', 'FICTION');
INSERT INTO books (title, category) VALUES ('Midnight’s Veil', 'FICTION');
INSERT INTO books (title, category) VALUES ('Ironheart', 'HISTORY');
INSERT INTO books (title, category) VALUES ('The Crown of Thorns', 'HISTORY');

INSERT INTO book_authors (book_id, author_id) VALUES (1, 1);
INSERT INTO book_authors (book_id, author_id) VALUES (1, 3);
INSERT INTO book_authors (book_id, author_id) VALUES (2, 2);
INSERT INTO book_authors (book_id, author_id) VALUES (3, 3);
INSERT INTO book_authors (book_id, author_id) VALUES (4, 4);
INSERT INTO book_authors (book_id, author_id) VALUES (5, 5);
INSERT INTO book_authors (book_id, author_id) VALUES (5, 4);
INSERT INTO book_authors (book_id, author_id) VALUES (6, 6);
INSERT INTO book_authors (book_id, author_id) VALUES (7, 1);
INSERT INTO book_authors (book_id, author_id) VALUES (8, 2);
INSERT INTO book_authors (book_id, author_id) VALUES (8, 4);
INSERT INTO book_authors (book_id, author_id) VALUES (8, 6);
INSERT INTO book_authors (book_id, author_id) VALUES (9, 3);
INSERT INTO book_authors (book_id, author_id) VALUES (10, 4);