package com.practice.test1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		super();
		this.categoryService = categoryService;
	}
	
	@PostMapping()
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		return new ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
		return new ResponseEntity<Category>(categoryService.getCategoryById(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") long id) {
		return new ResponseEntity<Category>(categoryService.updateCategory(category, id), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> DeleteCategory(@PathVariable("id") long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<String>("Category deleted successfully!", HttpStatus.OK);
	}
}
