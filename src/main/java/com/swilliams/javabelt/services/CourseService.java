package com.swilliams.javabelt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swilliams.javabelt.models.Course;
import com.swilliams.javabelt.repositories.CourseRepository;

@Service
public class CourseService {
public CourseRepository cR;
	
	public CourseService(CourseRepository cR) {
		this.cR = cR;
	}
	
	public void create(Course course) {
		cR.save(course);
	}
	
	public Optional<Course> find(Long id) {
		return cR.findById(id);
	}
	
	public ArrayList<Course> all(){
		return (ArrayList<Course>) cR.findAll();
	}
	
	public void destroy(Long id) {
		cR.deleteById(id);
	}
	
	public void update(Course course) {
		cR.save(course);
	}
}
