package com.offcl.competency_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcl.competency_tracker.model.Quarter;

public interface QuarterRepository extends JpaRepository<Quarter, Long> {

	 boolean existsByQuarterName(String quarterName);
}
