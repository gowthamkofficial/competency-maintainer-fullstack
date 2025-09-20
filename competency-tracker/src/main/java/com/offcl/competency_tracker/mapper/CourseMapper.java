package com.offcl.competency_tracker.mapper;

import com.offcl.competency_tracker.dto.course.CourseResponseDto;
import com.offcl.competency_tracker.model.Course;
import com.offcl.competency_tracker.model.FileEntity;

public class CourseMapper {

    public static CourseResponseDto toResponseDto(Course course) {
        if (course == null) {
            return null;
        }

        FileEntity certificate = course.getCertificate();

        return CourseResponseDto.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseDescription(course.getCourseDescription())
                .courseDurationInHours(course.getCourseDurationInHours())
                .paidCourse(course.getPaidCourse())
                .amountPaid(course.getAmountPaid())
                .courseStartedOn(course.getCourseStartedOn())
                .courseCompletedOn(course.getCourseCompletedOn())
                .status(course.getStatus())
                .rejectionComment(course.getRejectionComment())
                .level(course.getLevel())

                // User info
                .userId(course.getUser() != null ? course.getUser().getId() : null)
                .userName(course.getUser() != null ? course.getUser().getFirstName()+" "+course.getUser().getLastName() : null)

                // Quarter info
                .quarterId(course.getQuarter() != null ? course.getQuarter().getId() : null)
                .quarterName(course.getQuarter() != null ? course.getQuarter().getQuarterName() : null)

                // Certificate info
                .certificateId(certificate != null ? certificate.getId() : null)
                .certificateName(certificate != null ? certificate.getFileName() : null)
                .certificateDownloadUrl(certificate != null ? "/api/files/" + certificate.getId() + "/download" : null)

                // BaseEntity fields
                .createdOn(course.getCreatedOn())
                .updatedAt(course.getUpdatedOn())
                .build();
    }
}
