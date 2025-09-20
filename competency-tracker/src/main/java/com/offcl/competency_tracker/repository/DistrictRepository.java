package com.offcl.competency_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcl.competency_tracker.model.District;

public interface DistrictRepository extends JpaRepository<District, Long> {

	List<District> findByStateId(Long stateId);
}
