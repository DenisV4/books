package com.example.books.service;

import com.example.books.model.Book;

import java.util.List;

public interface BookService {

    Book findByTitleAndAuthor(String title, String author);

    List<Book> findAllByCategory(String category);

    Book save(Book book);

    Book update(Book book);

    void deleteById(Long id);
}
