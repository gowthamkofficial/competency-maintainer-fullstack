package com.offcl.competency_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcl.competency_tracker.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
