package com.Example.Employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Example.Employee.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Query("SELECT e.firstname FROM Employee e")
    List<String> findAllEmployeeFirstNames();

	Optional<Employee> findByFirstnameAndLastname(String firstname, String lastname);
}