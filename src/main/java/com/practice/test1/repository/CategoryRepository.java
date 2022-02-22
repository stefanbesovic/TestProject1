package com.practice.test1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.test1.domen.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
