package com.offcl.competency_tracker.dto.role;

import com.offcl.competency_tracker.dto.TimestampDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RoleResponseDto extends TimestampDto {

	private Long roleId;
	private String role;
}
