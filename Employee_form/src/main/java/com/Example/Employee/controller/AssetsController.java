package com.Example.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.Example.Employee.Implementation.CategoryImpl;
import com.Example.Employee.Implementation.SubcategoryImpl;
import com.Example.Employee.Service.AssetsService;
import com.Example.Employee.entity.Assets;
import com.Example.Employee.entity.Category;
import com.Example.Employee.entity.Subcategory;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AssetsController {

    @Autowired
    private AssetsService
    assetsService;

    @Autowired
    private CategoryImpl categoryService;

    @Autowired
    private SubcategoryImpl subcategoryService;

  
    @GetMapping("/assets")
    public String showForm(@RequestParam(value = "categoryId", required = false) Long categoryId, Model model) {
        // I used this for debug
    	System.out.println("categoryId "+categoryId);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        if (categoryId != null) {
            List<Subcategory> subcategories = subcategoryService.getSubcategoriesByCategoryId(categoryId);
            model.addAttribute("subcategories", subcategories);
        } else {
            model.addAttribute("subcategories", List.of()); 
        }

        model.addAttribute("selectedCategoryId", categoryId);
        return "assets"; 
    }

    //this is used for my assets data adding
    @PostMapping("/assets")
    public String addItem(@RequestParam("category.id") Long categoryId,
                          @RequestParam("subcategory.id") Long subcategoryId,
                          @RequestParam("subcategory.id") Subcategory subcategory2,
                          @RequestParam("companyname") String companyname,
                          @RequestParam("slNo") String slNo,
                          @RequestParam("quantity") Integer quantity,
                          @RequestParam("date") LocalDate date,
                          Model model) {

    	System.out.println("category€Id "+categoryId);
    	
        // fetching  category and subcategory entities from the database from their own table
        Category category = categoryService.getCategoryById(categoryId);
        Subcategory subcategory = subcategoryService.getSubcategoryById(subcategoryId);

        // checking if category and subcategory are valid
        if (category == null || subcategory == null) {
            model.addAttribute("error", "Invalid category or subcategory.");
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("subcategories", subcategoryService.getSubcategoriesByCategoryId(categoryId));
            model.addAttribute("selectedCategoryId", categoryId);
            return "assets";
        }

      
        Assets asset = new Assets();
        asset.setCategoryName(category.getName()); // storing category name
        asset.setSubcatgoryName(subcategory.getSub_name()); // storing subcategory name
        asset.setCompanyname(companyname);
        asset.setSlNo(slNo);
        asset.setQuantity(quantity.toString()); 
        asset.setCreatedDate(date);
        asset.setSubcategory(subcategory2);

       
        assetsService.saveAsset(asset);

        model.addAttribute("message", "Asset added successfully!");
        return "redirect:/dashboard";
    }
    
    
	/*
	 * @GetMapping("/fetchSerialNumbers")
	 * 
	 * @ResponseBody public List<String> fetchSerialNumbers(@RequestParam(value =
	 * "subcategoryId", required = false) Long subcategoryId) { return
	 * assetsService.getSerialNumbersBySubcategory(subcategoryId); }
	 * 
	 * @GetMapping("/fetchCompanies")
	 * 
	 * @ResponseBody public List<String> fetchCompanies(@RequestParam(value =
	 * "subcategoryId", required = false) Long subcategoryId) { return
	 * assetsService.getCompaniesBySubcategory(subcategoryId); }
	 */
}
