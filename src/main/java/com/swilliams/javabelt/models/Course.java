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
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Course {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Size(min=1, max=255, message="Course name cannot be blank.")
	private String subject;
	
    @Size(min=1, max=255, message="Instructor cannot be blank.")
	private String instructor;
    
	@Min(1)
	private int classLimit;
	
	private int signups;
    
	@Column(updatable=false)
	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date updatedAt;
	
   	@OneToMany(mappedBy="course",fetch=FetchType.LAZY)
   	private List<courses_users> courses_users;

	public Course() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public List<courses_users> getCourses_users() {
		return courses_users;
	}

	public void setCourses_users(List<courses_users> courses_users) {
		this.courses_users = courses_users;
	}

	public int getClassLimit() {
		return classLimit;
	}

	public void setClassLimit(int classLimit) {
		this.classLimit = classLimit;
	}

	public int getSignups() {
		return signups;
	}

	public void setSignups(int signups) {
		this.signups = signups;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
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
