package com.Example.Employee.Implementation;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Management;
import org.springframework.stereotype.Service;

import com.Example.Employee.Service.ManageAssetsService;
import com.Example.Employee.entity.Managment;
import com.Example.Employee.repository.ManagmentRepo;


	@Service
	public class ManageAssetsImpl implements ManageAssetsService{
	@Autowired
		  private ManagmentRepo managerepo;

		    public Managment save(Managment masset) {
		        return managerepo.save(masset);
		    }


		    public List<Managment> getAllItems() {
		        return managerepo.findAll();
		    }
		    public void deleteItem(Long id) {
		    	managerepo.deleteById(id);
		    }
			/*
			 * public void deleteEmployeeById(int id) { managerepo.deleteById(id); }
			 */


			 public void saveAll(List<Managment> manages) {
				 managerepo.saveAll(manages);
    }

	}

