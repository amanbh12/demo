package com.Example.Employee.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Example.Employee.Service.SubcategoryService;
import com.Example.Employee.entity.Category;
import com.Example.Employee.entity.Subcategory;
import com.Example.Employee.repository.CategoryRepo;
import com.Example.Employee.repository.SubcategoryRepo;

@Service
public class SubcategoryImpl implements SubcategoryService {
    
    @Autowired
    private SubcategoryRepo subcategoryRepo;
    
    @Autowired
    private CategoryRepo categoryRapo;
    
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepo.findAll();
    }
    
    public Subcategory saveSubcategory(Subcategory subcategory) {
        return subcategoryRepo.save(subcategory);
    }
    
    public List<Category> getAllCategories() {
        return categoryRapo.findAll();
    }

    public List<Subcategory> getSubcategoriesByCategoryId(Long categoryId) {
        return subcategoryRepo.findByCategoryId(categoryId);
    }

    public Subcategory getSubcategoryById(Long subcategoryId) {
        // Find subcategory by ID and return it, or throw an exception if not found
        return subcategoryRepo.findById(subcategoryId).orElse(null);
    }

  

	  public Subcategory save(Subcategory subcategory) {
        return subcategoryRepo.save(subcategory);
    }

}
