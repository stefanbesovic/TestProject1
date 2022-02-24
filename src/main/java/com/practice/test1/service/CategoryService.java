package com.practice.test1.service;

import java.util.List;

import com.practice.test1.domen.Category;

public interface CategoryService {
	Category saveCategory(Category category);
	List<Category> getAllCategories();
	Category getCategoryById(long id);
	Category updateCategory(Category category, long id);
	void deleteCategory(long id);
}
