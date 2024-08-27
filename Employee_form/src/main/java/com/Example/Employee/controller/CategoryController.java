package com.Example.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Example.Employee.Implementation.CategoryImpl;
import com.Example.Employee.entity.Category;

@Controller
public class CategoryController {
	@Autowired
		private CategoryImpl service;
		@GetMapping("/category")
		public String category() {
			
			return "categories";
		}
		
		@PostMapping("/category")
		public String addCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
		    try {
		        service.saveCategory(category);
		        
		        redirectAttributes.addFlashAttribute("message", "Category added successfully!");
		    } catch (Exception e) {
		        
		        e.printStackTrace(); 

		        
		        redirectAttributes.addFlashAttribute("errorMessage", "Fialed to add category. Please try again.");

		        
		        return "redirect:/categories"; 
		    }
		    return "redirect:/dashboard";
		}


}
