package com.example.books.service.impl;

import com.example.books.model.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    @Cacheable(value = "bookByTitleAndAuthor", key = "#title+#author")
    public Book findByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthor(title, author).orElseThrow();
    }

    @Override
    @Cacheable(value = "booksByCategory", key = "#category")
    public List<Book> findAllByCategory(String category) {
        return bookRepository.findAllByCategory_Name(category);
    }

    @Override
    @Caching(
            put = @CachePut(value = "bookByTitleAndAuthor", key = "#book.title+#book.author"),
            evict =  @CacheEvict(value = "booksByCategory", key = "#book.category.name", beforeInvocation = true)
    )
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Caching(
            put = @CachePut(value = "bookByTitleAndAuthor", key = "#book.title+#book.author"),
            evict =  @CacheEvict(value = "booksByCategory", key = "#book.category.name", beforeInvocation = true)
    )
    public Book update(Book book) {
        var existingBook = bookRepository.findById(book.getId()).orElseThrow();
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        return bookRepository.save(existingBook);
    }

    @Override
    @CacheEvict(cacheNames = {"bookByTitleAndAuthor", "booksByCategory"}, allEntries = true)
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
