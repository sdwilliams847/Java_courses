package com.swilliams.javabelt.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
	@Email
    @Size(min=1, max=255, message="Email cannot be blank")
    private String email;
    @Size(min=1, max=255, message="Username cannot be blank.")
    private String name;
    @Size(min=8, max=255, message="Password must be at least 8 characters long")
    private String password;
    
    @Transient
    @Size(min=8, max=255, message="Confirmation must be at least 8 characters long")
    private String confirm;

	@Column(updatable=false)
	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date updatedAt;

   	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
   	private List<courses_users> users_courses;
	
	public User() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}


	public List<courses_users> getUsers_courses() {
		return users_courses;
	}


	public void setUsers_courses(List<courses_users> users_courses) {
		this.users_courses = users_courses;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
}
