package com.example.books.web.controller;

import com.example.books.mapper.BookMapper;
import com.example.books.service.BookService;
import com.example.books.web.dto.BookResponse;
import com.example.books.web.dto.BookUpsertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public BookResponse get(@RequestParam String title, @RequestParam String author) {
        var book = bookService.findByTitleAndAuthor(title, author);
        return bookMapper.bookToResponse(book);
    }

    @GetMapping("/by-category")
    public List<BookResponse> getByCategory(@RequestParam String category) {
        var books = bookService.findAllByCategory(category);
        return bookMapper.bookListToBookResponseList(books);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@RequestBody BookUpsertRequest request) {
        var book = bookService.save(bookMapper.requestToBook(request));
        return bookMapper.bookToResponse(book);
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @RequestBody BookUpsertRequest request) {
        var book = bookService.update(bookMapper.requestToBook(id, request));
        return bookMapper.bookToResponse(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
