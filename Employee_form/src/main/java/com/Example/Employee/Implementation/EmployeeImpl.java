package com.Example.Employee.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Example.Employee.Service.EmployeeService;
import com.Example.Employee.dto.EmployeeDTO;
import com.Example.Employee.entity.Employee;
import com.Example.Employee.repository.EmployeeRepo;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo repo;

    
    
    
    public Employee addEmp(EmployeeDTO Empl) {
    	Employee emp=new Employee();
    	
try {
			
			BeanUtils.copyProperties(Empl,emp);
			
			
			repo.save(emp);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return emp;
	}
	
    

    public List<String> getAllEmployees() {
        return repo.findAllEmployeeFirstNames();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return repo.findById(id);
    }

    public void deleteEmployee(Integer id) { 
        repo.deleteById(id);
    }
    public Optional<Employee> findByFirstnameAndLastname(String firstname, String lastname) {
        return repo.findByFirstnameAndLastname(firstname, lastname);
    }
    public Employee save(Employee employee) {
        return repo.save(employee); // Save the employee using the repository
    }

	public Optional<Employee> findById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	public Object getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
   
}
