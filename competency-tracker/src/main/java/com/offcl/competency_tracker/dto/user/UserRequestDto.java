package com.offcl.competency_tracker.dto.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @NotNull(message = "Date of joining is required")
    private LocalDate dateOfJoining;

    @NotBlank(message = "Address line 1 is required")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "State ID is required")
    private Long stateId;

    @NotNull(message = "District ID is required")
    private Long districtId;

    @NotNull(message = "Role ID is required")
    private Long roleId;

    @NotNull(message = "Department ID is required")
    private Long departmentId;
    
     private Long profileImageId;

}
