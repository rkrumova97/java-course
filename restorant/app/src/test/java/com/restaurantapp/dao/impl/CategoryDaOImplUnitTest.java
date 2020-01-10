package com.restaurantapp.dao.impl;

import com.restaurantapp.dao.CategoryDao;
import com.restaurantapp.models.Category;

import org.junit.Test;

public class CategoryDaOImplUnitTest {
    @Test
    public void getCategory_isCorrect() throws Exception {
        CategoryDao categoryDao = new CategoryDaoImpl();
        Category category = categoryDao.readCategory(1L);
        assert category.getCategory().equals("something");
    }

}
