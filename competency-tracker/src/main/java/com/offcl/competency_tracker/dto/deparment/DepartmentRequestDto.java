package com.offcl.competency_tracker.dto.deparment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentRequestDto {

	@NotBlank(message="Department name is required")
	private String departmentName;
}
