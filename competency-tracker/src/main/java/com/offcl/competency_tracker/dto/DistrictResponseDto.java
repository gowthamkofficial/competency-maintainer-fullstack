package com.offcl.competency_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictResponseDto {

	private Long districtId;
	
	private String district;
	
	private Long stateId;
	
	private String state;
}
