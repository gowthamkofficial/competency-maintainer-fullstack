package com.offcl.competency_tracker.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.dto.user.UserLoginRequestDto;
import com.offcl.competency_tracker.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody UserLoginRequestDto dto) {
        return authService.login(dto);
    }

    @PostMapping("/refresh-token")
    public ApiResponse<Map<String, Object>> refreshToken(@RequestParam String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}
