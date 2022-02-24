package com.practice.test1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.test1.domen.Category;
import com.practice.test1.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping()
	public Category saveCategory(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}
	
	@GetMapping()
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	@GetMapping("{id}")
	public Category getCategoryById(@PathVariable("id") long id) {
		return categoryService.getCategoryById(id);
	}
	
	@PutMapping("{id}")
	public Category updateCategory(@RequestBody Category category, @PathVariable("id") long id) {
		return categoryService.updateCategory(category, id);
	}
	
	@DeleteMapping("{id}")
	public void DeleteCategory(@PathVariable("id") long id) {
		categoryService.deleteCategory(id);
	}
}
