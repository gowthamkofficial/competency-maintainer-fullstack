package com.offcl.competency_tracker.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.offcl.competency_tracker.common.enums.CourseStatus;
import com.offcl.competency_tracker.common.enums.ResponseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.common.response.PagedResponse;
import com.offcl.competency_tracker.dto.course.CourseRequestDto;
import com.offcl.competency_tracker.dto.course.CourseResponseDto;
import com.offcl.competency_tracker.exception.AlreadyExistsException;
import com.offcl.competency_tracker.exception.ResourceNotFoundException;
import com.offcl.competency_tracker.mapper.CourseMapper;
import com.offcl.competency_tracker.model.Course;
import com.offcl.competency_tracker.model.FileEntity;
import com.offcl.competency_tracker.model.Quarter;
import com.offcl.competency_tracker.model.User;
import com.offcl.competency_tracker.repository.CourseRepository;
import com.offcl.competency_tracker.repository.FileRepository;
import com.offcl.competency_tracker.repository.QuarterRepository;
import com.offcl.competency_tracker.repository.UserRepository;

@Service
public class CourseService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private QuarterRepository quarterRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private FileRepository fileRepo;
	
	 @Value("${file.upload-dir}")
	    private String uploadDir;
	
	 public ApiResponse<PagedResponse<CourseResponseDto>> getAllCourses(Pageable pageable) {

		    Page<Course> coursePage = this.courseRepo.findAll(pageable);

		    List<CourseResponseDto> courses = coursePage.getContent().stream()
		            .map(CourseMapper::toResponseDto)
		            .collect(Collectors.toList());

		    PagedResponse<CourseResponseDto> pagedResponse = PagedResponse.<CourseResponseDto>builder()
		            .content(courses)
		            .page(coursePage.getNumber())
		            .size(coursePage.getSize())
		            .totalElements(coursePage.getTotalElements())
		            .totalPages(coursePage.getTotalPages())
		            .last(coursePage.isLast())
		            .build();

		    return new ApiResponse<>(ResponseStatus.Success, "All courses fetched successfully", pagedResponse);
		}
	 
	 public ApiResponse<CourseResponseDto> updateCourseStatusByAdmin(Long courseId, CourseStatus newStatus) {

		    if (newStatus != CourseStatus.IN_REVIEW &&
		        newStatus != CourseStatus.APPROVED &&
		        newStatus != CourseStatus.REJECTED) {
		        throw new IllegalArgumentException("Invalid status for admin update");
		    }

		    Course course = this.courseRepo.findById(courseId)
		            .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

		    course.setStatus(newStatus);

		    // If approved, set courseCompletedOn to now? Optional depending on your flow
		    if (newStatus == CourseStatus.APPROVED && course.getCourseCompletedOn() == null) {
		        course.setCourseCompletedOn(LocalDate.now());
		    }

		    CourseResponseDto response = CourseMapper.toResponseDto(courseRepo.save(course));

		    return new ApiResponse<>(ResponseStatus.Success, "Course status updated successfully", response);
		}


public ApiResponse<PagedResponse<CourseResponseDto>> getCoursesByUser(
        Long userId, Pageable pageable) {

    // 1. Validate User
    User user = this.userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

    // 2. Fetch paginated courses
    Page<Course> coursePage = this.courseRepo.findAllByUserId(userId, pageable);

    // 3. Map to DTOs
    List<CourseResponseDto> courses = coursePage.getContent().stream()
            .map(CourseMapper::toResponseDto)
            .collect(Collectors.toList());

    // 4. Build PagedResponse
    PagedResponse<CourseResponseDto> pagedResponse = PagedResponse.<CourseResponseDto>builder()
            .content(courses)
            .page(coursePage.getNumber())
            .size(coursePage.getSize())
            .totalElements(coursePage.getTotalElements())
            .totalPages(coursePage.getTotalPages())
            .last(coursePage.isLast())
            .build();

    // 5. Return ApiResponse
    return new ApiResponse<>(ResponseStatus.Success, "Courses fetched successfully", pagedResponse);
}
	
	
	public ApiResponse<CourseResponseDto> createCourse(CourseRequestDto dto) {

	    // 1. Validate User
	    User existingUser = this.userRepo.findById(dto.getUserId())
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    // 2. Validate Quarter
	    Quarter existingQuarter = this.quarterRepo.findById(dto.getQuarterId())
	            .orElseThrow(() -> new ResourceNotFoundException("Quarter not found"));

	    // 3. Check if course already exists
	    if (this.courseRepo.existsByCourseNameAndUserIdAndQuarterId(
	            dto.getCourseName(), dto.getUserId(), dto.getQuarterId())) {
	        throw new AlreadyExistsException("You have already enrolled this course in this quarter!");
	    }

	

	    // 5. Build Course
	    Course course = Course.builder()
	            .courseName(dto.getCourseName())
	            .courseDescription(dto.getCourseDescription())
	            .courseDurationInHours(dto.getCourseDurationInHours())
	            .paidCourse(dto.getPaidCourse())
	            .amountPaid(dto.getAmountPaid())
	            .courseStartedOn(dto.getCourseStartedOn())
	            .status(CourseStatus.NOT_YET_STARTED) 
	            .level(dto.getLevel())
	            .quarter(existingQuarter)
	            .user(existingUser)
	            .certificate(null)
	            .build();
	    
	   CourseResponseDto savedResponse  = CourseMapper.toResponseDto(this.courseRepo.save(course));

	   return new ApiResponse<>(ResponseStatus.Success,"Created course successfully",savedResponse);
	   
	}

	
	public ApiResponse<CourseResponseDto> updateCourse(Long courseId, CourseRequestDto dto) {

	    // 1. Fetch the course
	    Course existingCourse = this.courseRepo.findById(courseId)
	            .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

	    // 2. Allow update only if status is NOT_YET_STARTED
	    if (existingCourse.getStatus() != CourseStatus.NOT_YET_STARTED) {
	        throw new IllegalStateException("Cannot update course after it has started or completed");
	    }

	    // 3. Validate User
	    User existingUser = this.userRepo.findById(dto.getUserId())
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    // 4. Validate Quarter
	    Quarter existingQuarter = this.quarterRepo.findById(dto.getQuarterId())
	            .orElseThrow(() -> new ResourceNotFoundException("Quarter not found"));

	    // 5. Check for duplicate course (excluding this course)
	    if (this.courseRepo.existsByCourseNameAndUserIdAndQuarterIdAndIdNot(
	            dto.getCourseName(), dto.getUserId(), dto.getQuarterId(), courseId)) {
	        throw new AlreadyExistsException("You have already enrolled this course in this quarter!");
	    }

	    // 6. Update fields
	    existingCourse.setCourseName(dto.getCourseName());
	    existingCourse.setCourseDescription(dto.getCourseDescription());
	    existingCourse.setCourseDurationInHours(dto.getCourseDurationInHours());
	    existingCourse.setPaidCourse(dto.getPaidCourse());
	    existingCourse.setAmountPaid(dto.getAmountPaid());
	    existingCourse.setCourseStartedOn(dto.getCourseStartedOn());
	    existingCourse.setLevel(dto.getLevel());
	    existingCourse.setQuarter(existingQuarter);
	    existingCourse.setUser(existingUser);

	    // Certificate update (optional)
	    if (dto.getCertificateId() != null) {
	        FileEntity
	        certificate = this.fileRepo.findById(dto.getCertificateId())
	                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
	        existingCourse.setCertificate(certificate);
	    }

	    // 7. Save and map to response
	    CourseResponseDto response = CourseMapper.toResponseDto(this.courseRepo.save(existingCourse));

	    return new ApiResponse<>(ResponseStatus.Success, "Course updated successfully", response);
	}
	
	
	public ApiResponse<CourseResponseDto> getCourseById(Long courseId) {

	   
	    Course course = this.courseRepo.findById(courseId)
	            .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));	  
	    
	    CourseResponseDto response = CourseMapper.toResponseDto(course);

	    return new ApiResponse<>(ResponseStatus.Success, "Course fetched successfully", response);
	}

	
	public ApiResponse<CourseResponseDto> updateCourseStatus(Long courseId, Long userId, CourseStatus newStatus) {

	    Course course = this.courseRepo.findById(courseId)
	            .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

	    if (!course.getUser().getId().equals(userId)) {
	        throw new IllegalStateException("You are not authorized to update this course");
	    }

	    if (newStatus == null) {
	        throw new IllegalArgumentException("Status must be provided");
	    }

	    CourseStatus currentStatus = course.getStatus();
	    if (currentStatus == CourseStatus.COMPLETED) {
	        throw new IllegalStateException("Cannot change status of a completed course");
	    }

	    if (newStatus == CourseStatus.COMPLETED) {
	        course.setCourseCompletedOn(LocalDate.now());
	    } else {
	        course.setCourseCompletedOn(null);
	    }

	    course.setStatus(newStatus);

	    CourseResponseDto response = CourseMapper.toResponseDto(this.courseRepo.save(course));

	    return new ApiResponse<>(ResponseStatus.Success, "Course status updated successfully", response);
	}


	
	public ApiResponse<CourseResponseDto> uploadCertificate(Long courseId, MultipartFile file) throws IOException {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must be provided");
        }

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        FileEntity certificate = new FileEntity();
        certificate.setFileName(file.getOriginalFilename());
        certificate.setFileType(file.getContentType());
        certificate.setFileSize(file.getSize());
        certificate.setFilePath(filePath.toString());

        fileRepo.save(certificate);

        course.setCertificate(certificate);
        CourseResponseDto response = CourseMapper.toResponseDto(courseRepo.save(course));

        return new ApiResponse<>(ResponseStatus.Success, "Certificate uploaded successfully", response);
    }

	
	
}
