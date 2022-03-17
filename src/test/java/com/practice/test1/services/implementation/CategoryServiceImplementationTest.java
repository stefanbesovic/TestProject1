package com.practice.test1.services.implementation;

import com.practice.test1.entities.Category;
import com.practice.test1.repositories.CategoryRepository;
import com.practice.test1.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplementationTest {
    @Mock
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImplementation(categoryRepository);
    }

    @Test
    void givenCategory_whenSaveCategory_thenSave() {
        //given
        Category c = new Category();
        c.setName("category");

        //when
        categoryService.saveCategory(c);

        //then
        assertNotNull(c);
        verify(categoryRepository).save(c);
    }

    @Test
    void givenNullCategory_whenSaveCategory_thenThrowException() {
        //given
        Category c = null;

        //when
        Exception e = assertThrows(NullPointerException.class, () -> {
            categoryService.saveCategory(c);
        });

        //then
        assertNull(c);
        assertTrue(e.getMessage().contains("Can't save. Category is null."));
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void getCategoryById() {
    }

    @Test
    void updateCategory() {
    }

    @Test
    void deleteCategory() {
    }
}