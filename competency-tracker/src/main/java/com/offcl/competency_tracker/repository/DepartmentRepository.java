package com.offcl.competency_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcl.competency_tracker.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	boolean existsByDepartmentName(String department);
	boolean existsByDepartmentNameAndIdNot(String department, Long id);
}
