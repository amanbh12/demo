package com.Example.Employee.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Subcategory {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String sub_name;
	    private String Sub_description;

	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSub_name() {
			return sub_name;
		}

		public void setSub_name(String sub_name) {
			this.sub_name = sub_name;
		}

		public String getSub_description() {
			return Sub_description;
		}

		public void setSub_description(String sub_description) {
			Sub_description = sub_description;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		@ManyToOne
	    @JoinColumn(name = "category_id")
	    private Category category;
}
