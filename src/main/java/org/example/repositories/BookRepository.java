package org.example.repositories;

import org.example.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository {

    Book findById(UUID id);

    List<Book> findAll();

    void save (Book book);

    void update (Book book);

    void deleteById(UUID id);
}
