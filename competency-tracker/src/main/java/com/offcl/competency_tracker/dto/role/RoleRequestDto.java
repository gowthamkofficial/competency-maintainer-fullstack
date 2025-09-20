package com.offcl.competency_tracker.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequestDto {
	
	@NotBlank(message="Role name is required")
	private String role;
}
