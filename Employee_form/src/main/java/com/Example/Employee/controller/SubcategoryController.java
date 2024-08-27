package com.Example.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Example.Employee.Implementation.CategoryImpl;
import com.Example.Employee.Implementation.SubcategoryImpl;
import com.Example.Employee.entity.Subcategory;

import java.util.List;

@Controller
public class SubcategoryController {

    @Autowired
    private SubcategoryImpl subcategoryService;

    @Autowired
    private CategoryImpl categoryService;

    @GetMapping("/subcategorie")
    public String showSubcategoryForm(Model model) {
        model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("subcategory", new Subcategory());

        return "subcategorie";
    }

    @PostMapping("/subcategory")
    public String addSubcategory(@ModelAttribute Subcategory subcategory, RedirectAttributes redirectAttributes) {
        try {
            
            subcategoryService.saveSubcategory(subcategory);
            
            
            redirectAttributes.addFlashAttribute("message", "Subcategory added successfully!");
            return "redirect:/dashboard";
            
        } catch (Exception e) {
            // Log the exception (consider using a logger in a real application)
            e.printStackTrace();
            
            // Add error message and redirect back to the form or dashboard
            redirectAttributes.addFlashAttribute("error", "An error occurred while adding the subcategory. Please try again.");
            return "redirect:/dashboard";
        }
    }
}

@RestController
class SubcatController {

    @Autowired
    private SubcategoryImpl subcategoryService;

    @GetMapping("/fetchSubcategories")
    @ResponseBody
    public List<Subcategory> fetchSubcategories(@RequestParam("categoryId") Long categoryId) {
    	System.out.println("categoryId "+categoryId);
        return subcategoryService.getSubcategoriesByCategoryId(categoryId);
    }
}
