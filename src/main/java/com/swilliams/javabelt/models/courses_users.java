package com.swilliams.javabelt.models;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.swilliams.javabelt.models.Course;
import com.swilliams.javabelt.models.User;

@Entity
public class courses_users {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="user_id")
   	private User user;
   	
	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="course_id")
   	private Course course;
	
	@Column(updatable=false)
	private Date createdAt;
	
	public courses_users() {
		super();
	}

	public courses_users(User user, Course course) {
		super();
		this.user = user;
		this.course = course;
		this.createdAt = new Date();
	}

	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
   	
	
}
