package com.Example.Employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Assets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyname;
    private String slNo;
    private String quantity;
    private LocalDate createdDate;

    private String categoryName;
    private String subcatgoryName;
    
    @ManyToOne
    @JoinColumn(name = "subcategory_id")// Store category name
    private Subcategory subcategory; // Store subcategory name

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public String getSubcatgoryName() {
		return subcatgoryName;
	}

	public void setSubcatgoryName(String subcatgoryName) {
		this.subcatgoryName = subcatgoryName;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getSlNo() {
        return slNo;
    }

    public void setSlNo(String slNo) {
        this.slNo = slNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

	public void setSubcategory(String sub_name) {
		// TODO Auto-generated method stub
		
	}

	

	

   
}
