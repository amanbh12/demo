package com.Example.Employee.Service;

import java.util.List;

import com.Example.Employee.entity.Category;

public interface CategoryService {
	 Category saveCategory(Category category);
	  List<Category> getAllCategories();
	 Category getCategoryById(Long categoryId);
	 Category findByName(String name);
	 Category save(Category category);
}
