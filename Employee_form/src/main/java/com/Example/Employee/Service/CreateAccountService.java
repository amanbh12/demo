package com.Example.Employee.Service;

import com.Example.Employee.dto.UserVo;
import com.Example.Employee.entity.Account;

public interface CreateAccountService {
	public Account save1(UserVo user);
	 public boolean authenticate(Account user);
}
