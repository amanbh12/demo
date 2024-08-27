package com.Example.Employee.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Example.Employee.Implementation.EmployeeImpl;
import com.Example.Employee.dto.EmployeeDTO;
import com.Example.Employee.entity.Employee;

@Controller
public class EmployeeController {

	
    @Autowired
    private EmployeeImpl service;
    
    
    @Value("${upload.dir}")
    private String uploadDir;

    @GetMapping("/")
    public String home() {
        return "createaccount";
    }

    //for my employee data adding in index.html
    @PostMapping("/users")
    public String empRegister(@ModelAttribute EmployeeDTO u , @RequestParam("image") MultipartFile image) {
        try {
            if (!image.isEmpty()) {
                
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs(); 
                }

                // here i generated a unique file name
                String originalFilename = image.getOriginalFilename();
                String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;
                String imagePath = Paths.get(uploadDir, uniqueFilename).toString();
                
                // here i am Saving the images in images folder name
                Path path = Paths.get(imagePath);
                Files.write(path, image.getBytes());
                
                // Storing my image in employee
                u.setImagePath(uniqueFilename);
            }


            service.addEmp(u);

        } catch (IOException e) {
            e.printStackTrace(); 
            return "error"; 
        }

        return "redirect:/view"; // 
    }
    
    
    
    @GetMapping("/view")
    public String viewEmployees(Model model) {
        model.addAttribute("employees", service.getAll());
        System.out.println(model);
        return "view";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {  
        service.deleteEmployee(id);
        return "redirect:/view";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Integer id, Model model) {  
        Optional<Employee> employeeOpt = service.getEmployeeById(id);
        if (employeeOpt.isPresent()) {
            model.addAttribute("employee", employeeOpt.get());
            return "edit";
        } else {
           
            return "redirect:/view";
        }
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Integer id, @ModelAttribute EmployeeDTO employee,Employee empl) {
    	empl.setId(id);  
        service.addEmp(employee);
        return "redirect:/view";
        
        
        
        
    }  
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        System.out.println(model);
        return "dashboard";
    }
    
}
