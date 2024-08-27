package com.Example.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Example.Employee.entity.Category;


public interface CategoryRepo  extends JpaRepository<Category, Long>{

	Category findByName(String name);



}
