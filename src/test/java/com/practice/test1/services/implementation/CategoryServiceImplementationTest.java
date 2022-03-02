package com.practice.test1.services.implementation;

import com.practice.test1.entities.Category;
import com.practice.test1.repositories.CategoryRepository;
import com.practice.test1.services.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@Transactional
@AutoConfigureTestDatabase
@SpringBootTest
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
        //Category expected = categoryService.getCategoryById(c.getId());
        assertNotNull(c);
        verify(categoryRepository).save(c);
        //then
        //assertEquals(expected, c);
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