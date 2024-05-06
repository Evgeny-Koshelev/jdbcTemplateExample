package org.example.repositories.impl;

import org.example.entities.Book;
import org.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book findById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book VALUES(?, ?, ?, ?)", book.getId(), book.getTitle(),
                book.getAuthor(), book.getPublicationYear());
    }

    @Override
    public void update(Book book) {
        jdbcTemplate.update("UPDATE Book SET title = ?, author = ?, publication_year = ? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getId());
    }

    @Override
    public void deleteById(UUID id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id =?", id);
    }
}
