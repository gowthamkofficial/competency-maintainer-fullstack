package com.offcl.competency_tracker.dto.deparment;

import com.offcl.competency_tracker.dto.TimestampDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DepartmentResponseDto extends TimestampDto {

	private Long departmentId;
	
	private String departmentName;
}
