package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.category.CategoryDto;
import com.practice.test1.web.dto.category.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.test1.entities.Category;
import com.practice.test1.services.CategoryService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/category")
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping()
	public CategoryDto saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
		Category category = categoryService.saveCategory(CategoryMapper.INSTANCE.fromDto(categoryDto));
		return CategoryMapper.INSTANCE.toDto(category);
	}
	
	@GetMapping("/all")
	public List<CategoryDto> getAllCategories() {
		return categoryService.getAllCategories()
				.stream()
				.map(CategoryMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public CategoryDto getCategoryById(@PathVariable("id") long id) {
		return CategoryMapper.INSTANCE.toDto(categoryService.getCategoryById(id));
	}
	
	@PutMapping("{id}")
	public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto,
									  @PathVariable("id") long id) {
		Category category = categoryService.updateCategory(CategoryMapper.INSTANCE.fromDto(categoryDto), id);
		return CategoryMapper.INSTANCE.toDto(category);
	}
	
	@DeleteMapping("{id}")
	public void DeleteCategory(@PathVariable("id") long id) {
		categoryService.deleteCategory(id);
	}
}