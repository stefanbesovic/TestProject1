package com.practice.test1.service.implementation;

import java.util.List;

import org.hibernate.query.criteria.internal.predicate.ExistsPredicate;
import org.springframework.stereotype.Service;

import com.practice.test1.domen.Category;
import com.practice.test1.repository.CategoryRepository;
import com.practice.test1.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {

	private CategoryRepository categoryRepository;
	
	public CategoryServiceImplementation(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Category saveCategory(Category category) {
		if(!categoryRepository.existsById(category.getId())) {
			return categoryRepository.save(category);
		}else {
			return null;
		}
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Category updateCategory(Category category, long id) {
		Category existing = categoryRepository.findById(id).orElse(null);
		if(existing.equals(null)) {
			return null;
		}
		existing.setName(category.getName());
		categoryRepository.save(existing);
		return existing;
	}

	@Override
	public void deleteCategory(long id) {
		Category exists = categoryRepository.findById(id).orElse(null);
		if(exists.equals(null)) {
			return;
		}
		categoryRepository.deleteById(id);
	}
}
