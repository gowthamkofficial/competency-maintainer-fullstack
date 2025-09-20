package com.offcl.competency_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.dto.deparment.DepartmentRequestDto;
import com.offcl.competency_tracker.dto.deparment.DepartmentResponseDto;
import com.offcl.competency_tracker.service.DepartmentService;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

	
	@Autowired
	private DepartmentService departmentService;
	

    @PostMapping("/createDepartment")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> createDepartment(
            @RequestBody DepartmentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.departmentService.createDepartment(dto));
    }

    @PutMapping("/updateDepartment/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.departmentService.updateDepartment(id, dto));
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDto>>> getAllDepartments() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.departmentService.getAllDepartments());
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDepartment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.departmentService.deleteDepartment(id));
    }
}
