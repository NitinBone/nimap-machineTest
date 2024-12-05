package com.nimap.machineTest.service;

import java.util.List;

import com.nimap.machineTest.model.Category;

public interface CategoryService {

	Category createCategory(Category category);

	Category getCategoryById(Long id);

	void deleteCategory(Long id);

	List<Category> getAllCategory(Integer pageNumber, Integer pageSize);

	Category updateCategory(Long id, Category updatedCategory);

}
