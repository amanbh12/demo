package com.Example.Employee.Service;

import java.util.List;

import com.Example.Employee.entity.Managment;

public interface ManageAssetsService {
	 public Managment save(Managment masset);
	 public List<Managment> getAllItems();
	 public void deleteItem(Long id);
	 public void saveAll(List<Managment> manages);
}
