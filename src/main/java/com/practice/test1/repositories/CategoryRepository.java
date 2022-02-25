package com.practice.test1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
