package com.Example.Employee.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Account{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
private  String firstname;
private  String lastname;
private  String email;
private  String mobile;
private  String password;
private String confirmpassword;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public Account() {
	super();
	// TODO Auto-generated constructor stub
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
public Account(int id, String firstname, String lastname, String email, String mobile, String password,
		String confirmpassword) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.mobile = mobile;
	this.password = password;
	this.confirmpassword = confirmpassword;
}
@Override
public String toString() {
	return "Account [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
			+ ", mobile=" + mobile + ", password=" + password + ", confirmpassword=" + confirmpassword + "]";
}

}