package com.Example.Employee.Service;

import java.util.List;

import com.Example.Employee.entity.Category;
import com.Example.Employee.entity.Subcategory;

public interface SubcategoryService {
	 List<Subcategory> getAllSubcategories();
	 Subcategory saveSubcategory(Subcategory subcategory);
	 List<Category> getAllCategories() ;
	 List<Subcategory> getSubcategoriesByCategoryId(Long categoryId) ;
	 Subcategory getSubcategoryById(Long subcategoryId) ;
	  Subcategory save(Subcategory subcategory) ;
}
