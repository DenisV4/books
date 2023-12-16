package com.example.books.mapper;

import com.example.books.model.Book;
import com.example.books.model.Category;
import com.example.books.service.CategoryService;
import com.example.books.web.dto.BookUpsertRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BookMapperDelegate implements BookMapper {

    @Autowired
    private CategoryService categoryService;

    @Override
    public Book requestToBook(BookUpsertRequest request) {
        var category = categoryService.findByName(request.getCategoryName());
        if (category == null) {
            var newCategory = Category
                    .builder()
                    .name(request.getCategoryName())
                    .build();

            category = categoryService.save(newCategory);
        }

        return Book
                .builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .category(category)
                .build();
    }

    @Override
    public Book requestToBook(Long id, BookUpsertRequest request) {
        var book = requestToBook(request);
        book.setId(id);
        return book;
    }
}
