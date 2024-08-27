package com.Example.Employee.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.Example.Employee.entity.HrModule;
@Repository
public interface HrRepo extends JpaRepository<HrModule, Integer> {
	
	

    @Query("SELECT h FROM HrModule h WHERE h.startDate BETWEEN :start AND :end AND h.employeename = :employee")
    List<HrModule> findByDateRangeAndEmployee(@Param("start") LocalDate start, 
                                              @Param("end") LocalDate end, 
                                              @Param("employee") String employee);
    @Query("SELECT h FROM HrModule h WHERE h.startDate BETWEEN :start AND :end")
    List<HrModule> findByDateRange(@Param("start") LocalDate start, 
                                   @Param("end") LocalDate end);
    
    @Query("SELECT DISTINCT h.status FROM HrModule h")
    List<String> findDistinctStatuses();
    
    
	
	
	
	
	
	boolean existsByEmployeenameAndStartDateAndStatus(String employeeName, LocalDate date, String status);
	
	/*
	 * @Query("SELECT h.employeename AND h.startDate  FROM HrModule h") boolean
	 * existsByEmployeeNameAndDate(String employeeName, LocalDate date
	 */
    
    
	
	

	
}
