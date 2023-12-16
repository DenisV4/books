package com.example.books.service.impl;

import com.example.books.model.Category;
import com.example.books.repository.CategoryRepository;
import com.example.books.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
