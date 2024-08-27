package com.Example.Employee.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Example.Employee.entity.Managment;

public interface ManagmentRepo  extends JpaRepository<Managment, Long> {

	
	// @Query("SELECT e.employee_name FROM managment e")
	//List<String> findAllEmployeeFirstNames();

	Managment save(Management masset);

}
