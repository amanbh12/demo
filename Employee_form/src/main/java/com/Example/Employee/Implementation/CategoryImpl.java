package com.Example.Employee.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Example.Employee.Service.CategoryService;
import com.Example.Employee.entity.Category;
import com.Example.Employee.repository.CategoryRepo;
@Service
public class CategoryImpl implements CategoryService{

	 @Autowired
	    private CategoryRepo categoryRepository;

	  
	    public Category saveCategory(Category category) {
	        return categoryRepository.save(category);
	    }


		public  List<Category> getAllCategories() {
			 return categoryRepository.findAll();
		}


		public Category getCategoryById(Long categoryId) {
		     return categoryRepository.findById(categoryId).orElse(null);
		}

		 public Category findByName(String name) {
		        return categoryRepository.findByName(name);
		    }

		    public Category save(Category category) {
		        return categoryRepository.save(category);
		    }

	}

