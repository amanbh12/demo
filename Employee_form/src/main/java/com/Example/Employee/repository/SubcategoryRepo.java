package com.Example.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Example.Employee.entity.Subcategory;



public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {
	  List<Subcategory> findByCategoryId(Long categoryId);


}
