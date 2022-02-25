package com.practice.test1.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.Category;
import com.practice.test1.repositories.CategoryRepository;
import com.practice.test1.services.CategoryService;

@RequiredArgsConstructor
@Service
public class CategoryServiceImplementation implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public Category saveCategory(Category category) {
		if(!categoryRepository.existsById(category.getId())) {
			return categoryRepository.save(category);
		}else {
			throw new DuplicateKeyException("Could not save category. Category with same ID already exists.");
		}
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Category not found: %d", id)));
	}

	@Override
	public Category updateCategory(Category category, long id) {
		Category existing = getCategoryById(id);
		existing.setName(category.getName());
		categoryRepository.save(existing);
		return existing;
	}

	@Override
	public void deleteCategory(long id) {
		getCategoryById(id);
		categoryRepository.deleteById(id);
	}
}
