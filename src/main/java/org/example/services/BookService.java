package org.example.services;

import org.example.entities.Book;
import org.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getById(UUID id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book save(Book book) {
        bookRepository.save(book);
        return getById(book.getId());
    }

    @Transactional
    public Book update(Book book) {
        bookRepository.update(book);
        return getById(book.getId());
    }

    @Transactional
    public Book delete(UUID id) {
        Book book = getById(id);
        if(book.getId() != null)
            bookRepository.deleteById(id);
        return book;
    }
}
