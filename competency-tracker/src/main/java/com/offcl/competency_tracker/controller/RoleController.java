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
import com.offcl.competency_tracker.dto.role.RoleRequestDto;
import com.offcl.competency_tracker.dto.role.RoleResponseDto;
import com.offcl.competency_tracker.service.RoleService;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

	
	@Autowired
	private RoleService roleService;
	

    @PostMapping("/createRole")
    public ResponseEntity<ApiResponse<RoleResponseDto>> createRole(
            @RequestBody RoleRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.roleService.createRole(dto));
    }

    @PutMapping("/updateRole/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDto>> updateRole(
            @PathVariable Long id,
            @RequestBody RoleRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.roleService.updateRole(id, dto));
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<ApiResponse<List<RoleResponseDto>>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.roleService.getAllRoles());
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.roleService.deleteRole(id));
    }

}
