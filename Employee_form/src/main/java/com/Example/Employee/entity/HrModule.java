package com.Example.Employee.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HrModule {
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private LocalDate  startDate ;
	private LocalDate modifydate;
	private String employeename;
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getModifydate() {
		return modifydate;
	}
	public void setModifydate(LocalDate modifydate) {
		this.modifydate = modifydate;
	}
	
	
		
		public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
		public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
		public HrModule() {
		super();
		// TODO Auto-gener
	}
		@Override
		public String toString() {
			return "HrModule [id=" + id + ", startDate=" + startDate + ", modifydate=" + modifydate + ", employeename="
					+ employeename + ", status=" + status + "]";
		}
		public static void save(HrModule leaveRequest) {
			// TODO Auto-generated method stub
			
		}
		
		
	
}
