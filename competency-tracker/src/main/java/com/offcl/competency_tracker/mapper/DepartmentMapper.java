package com.offcl.competency_tracker.mapper;

import com.offcl.competency_tracker.dto.deparment.DepartmentResponseDto;
import com.offcl.competency_tracker.model.Department;

public class DepartmentMapper {

	
	public static DepartmentResponseDto mapDepartmentResponse(Department department) {
		return DepartmentResponseDto.builder()
				.departmentName(department.getDepartmentName())
				.departmentId(department.getId())
				.createdOn(department.getCreatedOn())
				.updatedOn(department.getUpdatedOn())
				.build();
				
	}
}
