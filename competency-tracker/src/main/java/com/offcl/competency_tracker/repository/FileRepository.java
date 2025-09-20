package com.offcl.competency_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offcl.competency_tracker.model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
