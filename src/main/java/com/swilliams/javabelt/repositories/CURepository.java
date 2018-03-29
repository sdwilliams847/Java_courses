package com.swilliams.javabelt.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swilliams.javabelt.models.courses_users;


@Repository
public interface CURepository extends CrudRepository<courses_users,Long> {
	@Modifying
	@Transactional
	@Query(value="DELETE FROM courses_users WHERE course_id = ?1 AND user_id = ?2", nativeQuery=true)
	public void deleteFrom(Long course_id,Long user_id);
	
	@Query(value="SELECT * FROM courses_users WHERE course_id = ?1", nativeQuery=true)
	public List<courses_users> findCourse(Long course_id);
	
	@Query(value="SELECT * FROM courses_users WHERE user_id = ?1", nativeQuery=true)
	public List<courses_users> findUser(Long user_id);
	
}