package com.swilliams.javabelt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swilliams.javabelt.models.courses_users;
import com.swilliams.javabelt.repositories.CURepository;

@Service
public class CUService {
	private CURepository CU;
	
	public CUService(CURepository CU) {
		this.CU = CU;
	}
	
	public void deletefrom(Long course_id,Long user_id) {
		CU.deleteFrom(course_id, user_id);
	}
	
	public List<courses_users> findByCourseId(Long id) {
		return CU.findCourse(id);
	}
	
	public List<courses_users> findByUserId(Long id) {
		return CU.findUser(id);
	}
	
	public void create(courses_users cuser) {
		CU.save(cuser);
	}
	
}
