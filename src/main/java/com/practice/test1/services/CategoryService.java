package com.practice.test1.services;

import java.util.List;

import com.practice.test1.entities.Category;

public interface CategoryService {
	Category saveCategory(Category category);
	List<Category> getAllCategories();
	Category getCategoryById(Long id);
	Category updateCategory(Category category, Long id);
	void deleteCategory(Long id);
}
