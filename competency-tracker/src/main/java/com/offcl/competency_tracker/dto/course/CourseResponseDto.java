package com.offcl.competency_tracker.dto.course;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.offcl.competency_tracker.common.enums.CourseLevel;
import com.offcl.competency_tracker.common.enums.CourseStatus;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDto {

    private Long id;
    private String courseName;
    private String courseDescription;
    private Integer courseDurationInHours;
    private Boolean paidCourse;
    private Double amountPaid;
    private LocalDate courseStartedOn;
    private LocalDate courseCompletedOn;
    private CourseStatus status;
    private String rejectionComment;
    private CourseLevel level;

    // Instead of full User object, return basic info
    private Long userId;
    private String userName;

    // Quarter details
    private Long quarterId;
    private String quarterName;

    // Certificate details (download link)
    private Long certificateId;
    private String certificateName;
    private String certificateDownloadUrl;

    private LocalDateTime createdOn;
    private LocalDateTime updatedAt;
}
