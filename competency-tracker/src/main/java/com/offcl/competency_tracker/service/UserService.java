package com.offcl.competency_tracker.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.offcl.competency_tracker.common.enums.ResponseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.common.response.PagedResponse;
import com.offcl.competency_tracker.dto.user.UserRequestDto;
import com.offcl.competency_tracker.dto.user.UserResponseDto;
import com.offcl.competency_tracker.exception.AlreadyExistsException;
import com.offcl.competency_tracker.exception.ResourceNotFoundException;
import com.offcl.competency_tracker.mapper.UserMapper;
import com.offcl.competency_tracker.model.FileEntity;
import com.offcl.competency_tracker.model.User;
import com.offcl.competency_tracker.repository.DepartmentRepository;
import com.offcl.competency_tracker.repository.DistrictRepository;
import com.offcl.competency_tracker.repository.FileRepository;
import com.offcl.competency_tracker.repository.RoleRepository;
import com.offcl.competency_tracker.repository.StateRepository;
import com.offcl.competency_tracker.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private DistrictRepository districtRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	@Autowired
	private FileRepository fileRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
    @Value("${file.profile-dir}")
    private String profileDir;
	
	
	public ApiResponse<UserResponseDto> createUser(UserRequestDto dto) {
	    if (userRepo.existsByEmail(dto.getEmail())) {
	        throw new AlreadyExistsException("Email already exists: " + dto.getEmail());
	    }
	    if (userRepo.existsByMobileNumber(dto.getMobileNumber())) {
	        throw new AlreadyExistsException("Mobile number already exists: " + dto.getMobileNumber());
	    }

	    User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .mobileNumber(dto.getMobileNumber())
                .dateOfJoining(dto.getDateOfJoining())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

	    // resolve relations
	    user.setState(stateRepo.findById(dto.getStateId())
	            .orElseThrow(() -> new ResourceNotFoundException("State not found")));
	    user.setDistrict(districtRepo.findById(dto.getDistrictId())
	            .orElseThrow(() -> new ResourceNotFoundException("District not found")));
	    user.setRole(roleRepo.findById(dto.getRoleId())
	            .orElseThrow(() -> new ResourceNotFoundException("Role not found")));
	    user.setDepartment(departmentRepo.findById(dto.getDepartmentId())
	            .orElseThrow(() -> new ResourceNotFoundException("Department not found")));
	    
	    if (dto.getProfileImageId() != null) {
	        user.setProfileImage(fileRepo.findById(dto.getProfileImageId())
	                .orElseThrow(() -> new ResourceNotFoundException("Profile image not found")));
	    }
	
	    User saved = userRepo.save(user);
	   
	    String empcode = String.format("EMP%04d", saved.getId());
	    saved.setEmpcode(empcode);
	    saved = userRepo.save(saved);
	    
	    try {
	        emailService.sendRegistrationEmail(
	            user.getEmail(),
	            user.getFirstName(),
	            user.getEmail(),
	            dto.getPassword() 
	        );
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new ApiResponse<>(ResponseStatus.Success,
	            "User created successfully",
	            UserMapper.toResponse(saved));
	}
	
	
	public ApiResponse<UserResponseDto> updateUser(Long id, UserRequestDto dto) {
	    User existingUser = userRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

	    if (userRepo.existsByEmailAndIdNot(dto.getEmail(), id)) {
	        throw new AlreadyExistsException("Email already exists: " + dto.getEmail());
	    }
	    if (userRepo.existsByMobileNumberAndIdNot(dto.getMobileNumber(), id)) {
	        throw new AlreadyExistsException("Mobile number already exists: " + dto.getMobileNumber());
	    }

	    existingUser.setFirstName(dto.getFirstName());
	    existingUser.setLastName(dto.getLastName());
	    existingUser.setEmail(dto.getEmail());
	    existingUser.setMobileNumber(dto.getMobileNumber());
	    existingUser.setDateOfJoining(dto.getDateOfJoining());
	    existingUser.setAddressLine1(dto.getAddressLine1());
	    existingUser.setAddressLine2(dto.getAddressLine2());
	    existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));

	    existingUser.setState(stateRepo.findById(dto.getStateId())
	            .orElseThrow(() -> new ResourceNotFoundException("State not found")));
	    existingUser.setDistrict(districtRepo.findById(dto.getDistrictId())
	            .orElseThrow(() -> new ResourceNotFoundException("District not found")));
	    existingUser.setRole(roleRepo.findById(dto.getRoleId())
	            .orElseThrow(() -> new ResourceNotFoundException("Role not found")));
	    existingUser.setDepartment(departmentRepo.findById(dto.getDepartmentId())
	            .orElseThrow(() -> new ResourceNotFoundException("Department not found")));

	    if (dto.getProfileImageId() != null) {
	        existingUser.setProfileImage(fileRepo.findById(dto.getProfileImageId())
	                .orElseThrow(() -> new ResourceNotFoundException("Profile image not found")));
	    }

	    User updatedUser = userRepo.save(existingUser);

	    return new ApiResponse<>(ResponseStatus.Success,
	            "User updated successfully",
	            UserMapper.toResponse(updatedUser));
	}

	
	public ApiResponse<UserResponseDto> getUserById(Long id) {
	    User user = userRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

	    return new ApiResponse<>(ResponseStatus.Success,
	            "User fetched successfully",
	            UserMapper.toResponse(user));
	}

	

public ApiResponse<PagedResponse<UserResponseDto>> getAllUsers(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<User> userPage = userRepo.findAll(pageable);

    List<UserResponseDto> content = userPage.getContent()
            .stream()
            .map(UserMapper::toResponse)
            .toList();

    PagedResponse<UserResponseDto> pagedResponse = PagedResponse.<UserResponseDto>builder()
            .content(content)
            .page(userPage.getNumber())
            .size(userPage.getSize())
            .totalElements(userPage.getTotalElements())
            .totalPages(userPage.getTotalPages())
            .last(userPage.isLast())
            .build();

    return new ApiResponse<>(ResponseStatus.Success,
            "Users fetched successfully",
            pagedResponse);
}



public ApiResponse<UserResponseDto> uploadProfileImage(Long userId, MultipartFile file) {
    User user = userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

    // validate type & size (same as before)...

    try {
        Files.createDirectories(Paths.get(profileDir));

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = profileDir + "/" + fileName;

        Files.copy(file.getInputStream(), Paths.get(filePath));

        FileEntity profileImage = FileEntity.builder()
                .fileName(fileName)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .filePath(filePath)
                .directory(profileDir)
                .build();

        FileEntity savedFile = fileRepo.save(profileImage);

        user.setProfileImage(savedFile);
        userRepo.save(user);

        // Build full URLs
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String viewUrl = baseUrl + "/api/v1/files/view/" + savedFile.getId();
        String downloadUrl = baseUrl + "/api/v1/files/download/" + savedFile.getId();

        UserResponseDto response = UserMapper.toResponse(user);
        response.setProfileImagePath(viewUrl); // store view URL

        return new ApiResponse<>(ResponseStatus.Success,
                "Profile image uploaded successfully",
                response);

    } catch (IOException e) {
        throw new RuntimeException("Failed to upload file: " + e.getMessage());
    }
}


}
