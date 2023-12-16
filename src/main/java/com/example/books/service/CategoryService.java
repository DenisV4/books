package com.example.books.service;

import com.example.books.model.Category;

public interface CategoryService {

    Category findByName(String name);

    Category save(Category category);
}
