package com.offcl.competency_tracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcl.competency_tracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//create
	boolean existsByEmail(String email);
	
	boolean existsByMobileNumber(String mobileNumber);
	
	// update
	boolean existsByEmailAndIdNot(String email,Long id);

	boolean existsByMobileNumberAndIdNot(String mobileNumber,Long id);
	
	
  Optional<User> findByEmail(String email);
	
	
	


	
	
}
