package com.offcl.competency_tracker.mapper;

import com.offcl.competency_tracker.dto.user.UserRequestDto;
import com.offcl.competency_tracker.dto.user.UserResponseDto;
import com.offcl.competency_tracker.model.*;

public class UserMapper {

    public static User toEntity(UserRequestDto dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .mobileNumber(dto.getMobileNumber())
                .dateOfJoining(dto.getDateOfJoining())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .password(dto.getPassword())
                .build();
    }

    public static UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .empcode(user.getEmpcode())  
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .dateOfJoining(user.getDateOfJoining())
                .addressLine1(user.getAddressLine1())
                .addressLine2(user.getAddressLine2())
                .stateName(user.getState() != null ? user.getState().getStateName() : null)
                .districtName(user.getDistrict() != null ? user.getDistrict().getDistrictName() : null)
                .roleName(user.getRole() != null ? user.getRole().getRole() : null)
                .departmentName(user.getDepartment() != null ? user.getDepartment().getDepartmentName() : null)
                .profileImagePath(user.getProfileImage() != null ? user.getProfileImage().getFilePath() : null)
                .createdOn(user.getCreatedOn())
                .updatedOn(user.getUpdatedOn())
                .build();
    }

}
