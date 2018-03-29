package com.swilliams.javabelt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swilliams.javabelt.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long>{

}
