package com.Example.Employee.Implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Example.Employee.Service.HrService;
import com.Example.Employee.entity.HrModule;
import com.Example.Employee.repository.HrRepo;
@Service
public class HrImpl implements HrService {
	@Autowired
	private HrRepo repo;

	@Override
	public void save(HrModule hrmod) {
		
		repo.save(hrmod);
	}
	public List<HrModule> getdataByDateRange(LocalDate start, LocalDate end, String employee) {
       
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must not be after end date.");
        }

        
        if (employee == null || employee.trim().isEmpty()) {
            return repo.findByDateRange(start, end);
        } else {
            return repo.findByDateRangeAndEmployee(start, end, employee);
        }
    }
	 public HrModule getLeaveRequestById(Integer id) {
	        Optional<HrModule> hrmod = repo.findById(id);
	        if (hrmod.isPresent()) {
	            return hrmod.get();
	        } else {
	            throw new RuntimeException("Le not found with id: " + id);
	        }
	 }


	
	/*
	 * @Override public void savedata(@ModelAttribute HrModule hrmod) {
	 * System.out.println(hrmod); repo.save(hrmod); }
	 */

	

	@Override
	public boolean updateLeave(HrModule hrmod) {
		Optional<HrModule> existingLeaveRequest = repo.findById(hrmod.getId());
		if (existingLeaveRequest.isPresent()) {
			HrModule updatedLeaveRequest = existingLeaveRequest.get();

			// Update the properties as needed
			updatedLeaveRequest.setStatus(hrmod.getStatus());
			updatedLeaveRequest.setStartDate(hrmod.getStartDate());

			updatedLeaveRequest.setModifydate(hrmod.getModifydate());

			repo.save(updatedLeaveRequest);
			return true;
		} else {
			return false;
		}
	}
}
