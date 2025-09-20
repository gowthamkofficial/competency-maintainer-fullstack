package com.offcl.competency_tracker.dto.course;
import java.time.LocalDate;

import com.offcl.competency_tracker.common.enums.CourseLevel;
import com.offcl.competency_tracker.common.enums.CourseStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestDto {

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotBlank(message = "Course description is required")
    private String courseDescription;

    @NotNull(message = "Course duration is required")
    @Positive(message = "Duration must be greater than 0")
    private Integer courseDurationInHours;

    @NotNull(message = "Paid course flag is required")
    private Boolean paidCourse;

    private Double amountPaid;

    @NotNull(message = "Course start date is required")
    private LocalDate courseStartedOn;

    private LocalDate courseCompletedOn;

    private CourseStatus status = CourseStatus.NOT_YET_STARTED;

    private String rejectionComment;

    private CourseLevel level;

    @NotNull(message = "Quarter ID is required")
    private Long quarterId;

    @NotNull(message = "User ID is required")
    private Long userId;


    private Long certificateId;
}