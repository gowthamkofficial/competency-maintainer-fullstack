package com.offcl.competency_tracker.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.offcl.competency_tracker.common.enums.CourseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.common.response.PagedResponse;
import com.offcl.competency_tracker.dto.course.CourseRequestDto;
import com.offcl.competency_tracker.dto.course.CourseResponseDto;
import com.offcl.competency_tracker.service.CourseService;

@RestController
@RequestMapping("/api/v1/user")
public class UserCourseController {

	
	 @Autowired
	    private CourseService courseService;

    @PostMapping("/courses")
    public ApiResponse<CourseResponseDto> createCourse(@RequestBody CourseRequestDto dto) {
        return courseService.createCourse(dto);
    }

    @PutMapping("/courses/{courseId}")
    public ApiResponse<CourseResponseDto> updateCourse(
            @PathVariable Long courseId,
            @RequestBody CourseRequestDto dto) {
        return courseService.updateCourse(courseId, dto);
    }

    @GetMapping("/courses/{courseId}")
    public ApiResponse<CourseResponseDto> getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/users/{userId}/courses")
    public ApiResponse<PagedResponse<CourseResponseDto>> getCoursesByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseService.getCoursesByUser(userId, pageable);
    }

    @PatchMapping("/courses/{courseId}/status")
    public ApiResponse<CourseResponseDto> updateCourseStatus(
            @PathVariable Long courseId,
            @RequestParam Long userId,
            @RequestParam CourseStatus status) {
        return courseService.updateCourseStatus(courseId, userId, status);
    }

    @PostMapping(
    	    value = "/courses/{courseId}/certificate",
    	    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    	)
    	public ApiResponse<CourseResponseDto> uploadCertificate(
    	        @PathVariable Long courseId,
    	        @RequestPart("file") MultipartFile file) throws IOException {
    	    return courseService.uploadCertificate(courseId, file);
    	}
}
