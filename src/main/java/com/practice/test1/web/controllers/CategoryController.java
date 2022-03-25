package com.practice.test1.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.test1.web.dto.category.CategoryDto;
import com.practice.test1.web.dto.category.CategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Category Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Category.")
public class CategoryController {

	private final CategoryService categoryService;

	@Operation(summary = "Creates new Category")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Category"),
			@ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PostMapping()
	public CategoryDto saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
		Category category = categoryService.saveCategory(CategoryMapper.INSTANCE.fromDto(categoryDto));
		return CategoryMapper.INSTANCE.toDto(category);
	}

	@Operation(summary = "Retrieves list of all Categories")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "List of Categories"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("/all")
	public List<CategoryDto> getAllCategories() {
		return categoryService.getAllCategories()
				.stream()
				.map(CategoryMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}

	@Operation(summary = "Retrieves details about Category")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Category"),
			@ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("{id}")
	public CategoryDto getCategoryById(@PathVariable("id") Long id) {
		return CategoryMapper.INSTANCE.toDto(categoryService.getCategoryById(id));
	}

	@Operation(summary = "Updates existing Category")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Category"),
			@ApiResponse(responseCode = "400", description = "Validation error : invalid argument"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PutMapping("{id}")
	public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto,
									  @PathVariable("id") Long id) {
		Category category = categoryService.updateCategory(CategoryMapper.INSTANCE.fromDto(categoryDto), id);
		return CategoryMapper.INSTANCE.toDto(category);
	}

	@Operation(summary = "Deletes Category")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Category deleted"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@DeleteMapping("{id}")
	public void DeleteCategory(@PathVariable("id") Long id) {
		categoryService.deleteCategory(id);
	}
}