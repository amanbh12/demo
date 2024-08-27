package com.Example.Employee.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Example.Employee.Service.AssetsService;
import com.Example.Employee.entity.Assets;
import com.Example.Employee.entity.Category;
import com.Example.Employee.repository.AssetsRepo;
import com.Example.Employee.repository.CategoryRepo;

@Service
public class AssetsImpl implements AssetsService {
    @Autowired
    private CategoryRepo categoryRapo;
    @Autowired
    private AssetsRepo assetRepository;

	
	
	 public List<Category> getAllCategories() {
	        return categoryRapo.findAll();
	    }
	    
	    public Assets saveAsset(Assets asset) {
	        return assetRepository.save(asset);
	    }

	    public List<Assets> getAllAssets() {
	        return assetRepository.findAll();
	    }

	    public Optional<Assets> getAssetById(Integer id) {
	        return assetRepository.findById(id);
	    }

	    public void deleteAsset(Integer id) {
	        assetRepository.deleteById(id);
	    }

		/*
		 * public List<String> getAllSerialNumbers() {
		 * 
		 * return assetRepository.findAllSerialNumbers(); }
		 * 
		 * public List<String> getAllCompnay() {
		 * 
		 * return assetRepository.findAllCompanyName(); }
		 */
			
	    public Assets save(Assets asset) {
			        return assetRepository.save(asset);
			    }

			 public List<String> getSerialNumbersBySubcategory(Long subcategoryId) {
			        return assetRepository.findSerialNumbersBySubcategoryId(subcategoryId);
			    }

			    public List<String> getCompaniesBySubcategory(Long subcategoryId) {
			        return assetRepository.findCompaniesBySubcategoryId(subcategoryId);
			    }

				public Optional<Assets> findByCategoryAndSubcategoryAndCompanyNameAndSerialNumber(String category,
						String subcategory, String companyName, String serialNumber) {
					// TODO Auto-generated method stub
					System.out.println("Category: " + category);
				    System.out.println("Subcategory ID: " + subcategory);
				    System.out.println("Company Name: " + companyName);
				    System.out.println("Serial Number: " + serialNumber);
					return assetRepository.findByCategoryAndSubcategoryAndCompanyNameAndSerialNumber(category, subcategory, companyName, serialNumber);
				}

	}

	
	
	
	
