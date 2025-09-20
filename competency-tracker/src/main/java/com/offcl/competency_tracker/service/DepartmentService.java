package com.offcl.competency_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcl.competency_tracker.common.enums.ResponseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.dto.deparment.DepartmentRequestDto;
import com.offcl.competency_tracker.dto.deparment.DepartmentResponseDto;
import com.offcl.competency_tracker.exception.AlreadyExistsException;
import com.offcl.competency_tracker.exception.ResourceNotFoundException;
import com.offcl.competency_tracker.mapper.DepartmentMapper;
import com.offcl.competency_tracker.model.Department;
import com.offcl.competency_tracker.repository.DepartmentRepository;

@Service
public class DepartmentService {

	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	

	 // Create
	 public ApiResponse<DepartmentResponseDto> createDepartment(DepartmentRequestDto dto) {
	     if (this.departmentRepo.existsByDepartmentName(dto.getDepartmentName())) {
	         throw new AlreadyExistsException("Department already exists!");
	     }

	     Department savedDepartment = this.departmentRepo.save(
	             Department.builder()
	                       .departmentName(dto.getDepartmentName())
	                       .build()
	     );

	     return new ApiResponse<>(ResponseStatus.Success,
	             "Created department successfully",
	             DepartmentMapper.mapDepartmentResponse(savedDepartment));
	 }

	 // Update
	 public ApiResponse<DepartmentResponseDto> updateDepartment(Long id, DepartmentRequestDto dto) {
	     Department existingDepartment = this.departmentRepo.findById(id)
	             .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));

	     // prevent duplicate names
	     if (this.departmentRepo.existsByDepartmentNameAndIdNot(dto.getDepartmentName(), id)) {
	         throw new AlreadyExistsException("Department with name already exists!");
	     }

	     existingDepartment.setDepartmentName(dto.getDepartmentName());
	     Department updatedDepartment = this.departmentRepo.save(existingDepartment);

	     return new ApiResponse<>(ResponseStatus.Success,
	             "Updated department successfully",
	             DepartmentMapper.mapDepartmentResponse(updatedDepartment));
	 }

	 // List all
	 public ApiResponse<List<DepartmentResponseDto>> getAllDepartments() {
	     List<DepartmentResponseDto> departments = this.departmentRepo.findAll()
	             .stream()
	             .map(DepartmentMapper::mapDepartmentResponse)
	             .toList();

	     return new ApiResponse<>(ResponseStatus.Success,
	             "Fetched all departments successfully",
	             departments);
	 }

	 // Delete
	 public ApiResponse<Void> deleteDepartment(Long id) {
	     Department existingDepartment = this.departmentRepo.findById(id)
	             .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));

	     this.departmentRepo.delete(existingDepartment);

	     return new ApiResponse<>(ResponseStatus.Success,
	             "Deleted department successfully",
	             null);
	 }

	    
		

}
