package com.practice.test1.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.practice.test1.entities.Category;
import com.practice.test1.repositories.CategoryRepository;
import com.practice.test1.services.CategoryService;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImplementation implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public Category saveCategory(Category category) {
		if(category == null)
			throw new NullPointerException("Can't save. Category is null.");

		if(!categoryRepository.existsById(category.getId())) {
			log.info("Category with id {} saved to repository.", category.getId());
			return categoryRepository.save(category);
		}else {
			log.error("Category with same ID {} already exists.", category.getId());
			throw new DuplicateKeyException("Could not save category. Category with same ID already exists.");
		}
	}

	@Override
	public List<Category> getAllCategories() {
		log.info("Getting all categories.");

		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Category not found: %d", id)));
	}

	@Override
	public Category updateCategory(Category category, Long id) {
		Category existing = getCategoryById(id);
		existing.setName(category.getName());

		categoryRepository.save(existing);

		log.info("Updating category with id {}", id);

		return existing;
	}

	@Override
	public void deleteCategory(Long id) {
		log.info("Deleting category with id {}.", id);
		Category categoryById = getCategoryById(id);
		categoryRepository.deleteById(categoryById.getId());
	}
}
