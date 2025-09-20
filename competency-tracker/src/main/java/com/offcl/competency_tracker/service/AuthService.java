package com.offcl.competency_tracker.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.offcl.competency_tracker.common.enums.ResponseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.dto.user.UserLoginRequestDto;
import com.offcl.competency_tracker.exception.ResourceNotFoundException;
import com.offcl.competency_tracker.mapper.UserMapper;
import com.offcl.competency_tracker.model.User;
import com.offcl.competency_tracker.repository.UserRepository;
import com.offcl.competency_tracker.security.JwtService;

@Service
public class AuthService {

	
    @Autowired
    private UserRepository userRepo;
    
    @Autowired 
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;
    
    
    public ApiResponse<Map<String,Object>> login(UserLoginRequestDto dto){
    	
    	User user = this.userRepo.findByEmail(dto.getEmail()).orElseThrow(()->new ResourceNotFoundException("Invalid user email or password"));
    	
    	 authenticationManager.authenticate(
    		        new UsernamePasswordAuthenticationToken(
    		            dto.getEmail(),
    		            dto.getPassword()
    		        )
    		    );
    	 
    	 
    	 Map<String, Object> userMap = new HashMap<>();
    	 userMap.put("id", user.getId());
    	 userMap.put("name", user.getFirstName()+" "+user.getLastName());
    	 var jwtToken = jwtService.generateToken(userMap,user);
    	    var refreshToken = jwtService.generateRefreshToken(user);
    	    
    	    HashMap<String, Object> loginResponse = new HashMap();
    	    
    	    loginResponse.put("userDetails", UserMapper.toResponse(user));
    	    
    	    loginResponse.put("jwtToken", jwtToken);
    	    loginResponse.put("refreshToken", refreshToken);
    	
    	return new ApiResponse<>(ResponseStatus.Success,"Logged in successfully",loginResponse);
    	
    }
    
    
    public ApiResponse<Map<String, Object>> refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new IllegalArgumentException("Refresh token must be provided");
        }

        String userEmail = jwtService.extractUsername(refreshToken);
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + userEmail));

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new IllegalStateException("Invalid refresh token");
        }

        // create claims again (user details map)
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("name", user.getFirstName() + " " + user.getLastName());

        String newAccessToken = jwtService.generateToken(claims, user);

        Map<String, Object> response = new HashMap<>();
        response.put("jwtToken", newAccessToken);
        response.put("refreshToken", refreshToken); // re-use same refresh token or issue new one
        response.put("userDetails", UserMapper.toResponse(user));

        return new ApiResponse<>(ResponseStatus.Success, "Access token refreshed successfully", response);
    }

	
}
