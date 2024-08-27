package com.Example.Employee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Example.Employee.Implementation.CreateAccountImpl;
import com.Example.Employee.dto.UserVo;
import com.Example.Employee.entity.Account;
@Controller
public class CreateAccountController {

	
	private final CreateAccountImpl createService;
	
	
	public CreateAccountController(CreateAccountImpl createService) {
	
		this.createService = createService;
	}


	@PostMapping("/create")
	public String stuRegister(@ModelAttribute UserVo user ) {
		
		try {
			System.out.println(user);
			Account createuser=new Account();
			createuser=	createService.save1(user);
			     
			if(createuser!=null)
			{
				 return "redirect:/login";
			}
			else {
				return "redirect:/createaccount";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return "redirect:/login";
		
		
		 
	}
	
	
	//for my login page
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	@PostMapping("/logincheck")
    public String userlogin(@ModelAttribute Account user, Model model) {
        try {
            System.out.println(user);

            
            boolean isAuthenticated = createService.authenticate(user);

            if (isAuthenticated) {
                return "dashboard"; 
            } 
            else {
                model.addAttribute("error", "Invalid credentials. Please try again.");
                return "login"; 
            }
        } 
        catch (Exception e) {
            
            e.printStackTrace();
            model.addAttribute("error", "An error occurred. Please try again later.");
            return "login";
        }
    }

		
		@GetMapping("/index")
		public String index(Model model) {
			System.out.println(model);
			return "index";
		}
}

		
		
	
	
	
	
	

