package com.Example.Employee.Implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Example.Employee.Service.CreateAccountService;
import com.Example.Employee.dto.UserVo;
import com.Example.Employee.entity.Account;
import com.Example.Employee.repository.CreateAccountRepo;
@Service
public class CreateAccountImpl implements CreateAccountService {

	@Autowired
	private CreateAccountRepo accountrapo;
	public Account save1(UserVo user) {
		Account usercreate=new  Account();
		try {
			
			BeanUtils.copyProperties(user,usercreate);
			
			usercreate.setFirstname(user.getname());
			 accountrapo.save(usercreate);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return usercreate;
	}
	
	 public boolean authenticate(Account user) {
	        if (user == null || user.getEmail() == null || user.getPassword() == null) {
	            return false;
	        }

	        try {
	            Account existingUser = accountrapo.findByEmail(user.getEmail());
	            if (existingUser == null) {
	                return false;
	            }
	            if(existingUser.getPassword().equals(user.getPassword()))
	            		{
	            	return true;
	            		}
	            
	        //    return existingUser.getPassword().equals(user.getPassword());
	        } catch (Exception e) {
	            // Handle the exception
	            e.printStackTrace();
	            return false;
	        }
			return false;
	    }

}
