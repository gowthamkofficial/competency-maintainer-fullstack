package com.offcl.competency_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.offcl.competency_tracker.common.enums.CourseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.common.response.PagedResponse;
import com.offcl.competency_tracker.dto.course.CourseResponseDto;
import com.offcl.competency_tracker.service.CourseService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminCourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public ApiResponse<PagedResponse<CourseResponseDto>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseService.getAllCourses(pageable);
    }

    @PatchMapping("/courses/{courseId}/status")
    public ApiResponse<CourseResponseDto> updateCourseStatusByAdmin(
            @PathVariable Long courseId,
            @RequestParam CourseStatus status) {
        return courseService.updateCourseStatusByAdmin(courseId, status);
    }
}
