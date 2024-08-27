package com.Example.Employee.Service;

import java.util.List;
import java.util.Optional;

import com.Example.Employee.entity.Assets;
import com.Example.Employee.entity.Category;

public interface AssetsService {

	 List<Category> getAllCategories();
	 Assets saveAsset(Assets asset);
	 List<Assets> getAllAssets();
	 Optional<Assets> getAssetById(Integer id);
	 void deleteAsset(Integer id);
	  Assets save(Assets asset);;
	 List<String> getSerialNumbersBySubcategory(Long subcategoryId);
	  Optional<Assets> findByCategoryAndSubcategoryAndCompanyNameAndSerialNumber(String category,
				String subcategory, String companyName, String serialNumber) ;
	
}
