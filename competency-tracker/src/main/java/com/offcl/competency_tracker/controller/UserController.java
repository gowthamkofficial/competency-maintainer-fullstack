package com.offcl.competency_tracker.controller;

import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.common.response.PagedResponse;
import com.offcl.competency_tracker.dto.user.UserRequestDto;
import com.offcl.competency_tracker.dto.user.UserResponseDto;
import com.offcl.competency_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    /*** Create user ***/
    @PostMapping("/createUser")
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@RequestBody UserRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(dto));
    }

    /*** Update user ***/
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable Long id,
                                                                   @RequestBody UserRequestDto dto) {
        return ResponseEntity
                .ok(userService.updateUser(id, dto));
    }

    /*** Get user by id ***/
    @GetMapping("/viewUser/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .ok(userService.getUserById(id));
    }

    /*** List users with pagination ***/
    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse<PagedResponse<UserResponseDto>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }

    /*** Upload profile image ***/
    @PostMapping(
            value = "/{id}/profile-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<UserResponseDto>> uploadProfileImage(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file) {

        return ResponseEntity.ok(userService.uploadProfileImage(id, file));
    }
}
