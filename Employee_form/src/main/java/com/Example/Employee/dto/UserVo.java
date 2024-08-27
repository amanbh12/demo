package com.Example.Employee.dto;

public class UserVo {
	private  String name;
	private  String lastname;
	private  String email;
	private  String mobile;
	private  String password;
	private String confirmpassword;
	private String category;
	private String Description;
	private String subcategory;
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	@Override
	public String toString() {
		return "UserVo [name=" + name + ", lastname=" + lastname + ", email=" + email + ", mobile=" + mobile
				+ ", password=" + password + ", confirmpassword=" + confirmpassword + ", category=" + category
				+ ", Description=" + Description + ", subcategory=" + subcategory + "]";
	}
	public UserVo(String name, String lastname, String email, String mobile, String password, String confirmpassword,
			String category, String description, String subcategory) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.category = category;
		Description = description;
		this.subcategory = subcategory;
	}
	
	
}
