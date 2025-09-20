package com.offcl.competency_tracker.mapper;

import com.offcl.competency_tracker.dto.role.RoleResponseDto;
import com.offcl.competency_tracker.model.Role;

public class RoleMapper {

	
	public static RoleResponseDto mapRoleResponse(Role role) {
		return RoleResponseDto.builder()
				.role(role.getRole())
				.roleId(role.getId())
				.createdOn(role.getCreatedOn())
				.updatedOn(role.getUpdatedOn())
				.build();
	}
}
