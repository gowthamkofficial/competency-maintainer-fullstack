package com.offcl.competency_tracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.offcl.competency_tracker.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByQuarterIdAndCourseNameAndUserId(Long quarterId, String courseName, Long userId);
    
//	Create
	boolean existsByCourseNameAndUserIdAndQuarterId(String courseName, Long userId, Long quarterId);

//	Update
	boolean existsByCourseNameAndUserIdAndQuarterIdAndIdNot(
	        String courseName, Long userId, Long quarterId, Long id);
	
	
//	get course
	
	 Page<Course> findAllByUserId(Long userId, Pageable pageable);
	 
	 Page<Course> findAll(Pageable pageable);

}
