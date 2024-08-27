package com.Example.Employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Employee {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	
		private Integer id;
		
		private String firstname;
		
		private String lastname;
		
		private String fathersname;
		 
		private String email;
		
		private String contact;
		
		private String address;
		
		private String gender;
		 private String imagePath;

	
	public String getImagePath() {
			return imagePath;
		}
		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

	public Employee(Integer id, String firstname, String lastname, String fathersname, String email, String contact,
			String address, String gender) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.fathersname = fathersname;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.gender = gender;
	}
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFathersname() {
		return fathersname;
	}
	public void setFathersname(String fathersname) {
		this.fathersname = fathersname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	

}