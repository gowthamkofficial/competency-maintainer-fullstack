package com.offcl.competency_tracker.dto.user;

import java.time.LocalDate;

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
public class UserResponseDto extends TimestampDto {

    private Long id;
    private String empcode;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private LocalDate dateOfJoining;
    private String addressLine1;
    private String addressLine2;
    private String stateName;
    private String districtName;
    private String roleName;
    private String departmentName;
    private String profileImagePath;
}
