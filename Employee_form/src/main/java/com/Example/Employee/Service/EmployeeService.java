package com.Example.Employee.Service;

import java.util.List;
import java.util.Optional;

import com.Example.Employee.dto.EmployeeDTO;
import com.Example.Employee.entity.Employee;

public interface EmployeeService {
	// Employee addEmp(Employee employee);
	 List<String> getAllEmployees();
	  Optional<Employee> getEmployeeById(Integer id);
	  void deleteEmployee(Integer id);
	  Optional<Employee> findByFirstnameAndLastname(String firstname, String lastname);
	   Employee save(Employee employee);
	   Optional<Employee> findById(Integer id);
	   Object getAll() ;
	    Employee addEmp(EmployeeDTO Empl);
}
