package com.Example.Employee.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.Example.Employee.entity.HrModule;




public interface HrService {

	//void save(String employeename, String status, LocalDate startDate);

	void save(HrModule hrmod);

	

	List<HrModule> getdataByDateRange(LocalDate start, LocalDate end, String employee);



	



	



	HrModule getLeaveRequestById(Integer id);



	



	boolean updateLeave(HrModule hrmod);

	

	

}
