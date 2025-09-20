package com.offcl.competency_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcl.competency_tracker.common.enums.ResponseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.dto.role.RoleRequestDto;
import com.offcl.competency_tracker.dto.role.RoleResponseDto;
import com.offcl.competency_tracker.exception.AlreadyExistsException;
import com.offcl.competency_tracker.exception.ResourceNotFoundException;
import com.offcl.competency_tracker.mapper.RoleMapper;
import com.offcl.competency_tracker.model.Role;
import com.offcl.competency_tracker.repository.RoleRepository;

@Service
public class RoleService {

	
	@Autowired
	private RoleRepository roleRepo;
	
	
	
	
	 /*** Create ***/
   public ApiResponse<RoleResponseDto> createRole(RoleRequestDto dto) {
       if (this.roleRepo.existsByRole(dto.getRole())) {
           throw new AlreadyExistsException("Role already exists!");
       }

       Role savedRole = this.roleRepo.save(Role.builder()
                                               .role(dto.getRole())
                                               .build());

       return new ApiResponse<>(ResponseStatus.Success,
               "Created role successfully",
               RoleMapper.mapRoleResponse(savedRole));
   }

   /*** Update ***/
   public ApiResponse<RoleResponseDto> updateRole(Long id, RoleRequestDto dto) {
       Role existingRole = this.roleRepo.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));

       // Check if another role with the same name exists
       if (this.roleRepo.existsByRoleAndIdNot(dto.getRole(), id)) {
           throw new AlreadyExistsException("Role with name already exists!");
       }

       existingRole.setRole(dto.getRole());
       Role updatedRole = this.roleRepo.save(existingRole);

       return new ApiResponse<>(ResponseStatus.Success,
               "Updated role successfully",
               RoleMapper.mapRoleResponse(updatedRole));
   }

   /*** List all roles ***/
   public ApiResponse<List<RoleResponseDto>> getAllRoles() {
       List<Role> roles = this.roleRepo.findAll();

       List<RoleResponseDto> roleDtos = roles.stream()
                                             .map(RoleMapper::mapRoleResponse)
                                             .toList();

       return new ApiResponse<>(ResponseStatus.Success,
               "Fetched all roles successfully",
               roleDtos);
   }

   /*** Delete ***/
   public ApiResponse<Void> deleteRole(Long id) {
       Role existingRole = this.roleRepo.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));

       this.roleRepo.delete(existingRole);

       return new ApiResponse<>(ResponseStatus.Success,
               "Deleted role successfully",
               null);
   }
	
	
   
}
