package com.offcl.competency_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcl.competency_tracker.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	boolean existsByRole(String role);
	boolean existsByRoleAndIdNot(String role, Long id);
}
