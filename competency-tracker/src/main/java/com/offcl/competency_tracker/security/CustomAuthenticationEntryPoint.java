package com.offcl.competency_tracker.security;

import java.io.IOException;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.offcl.competency_tracker.common.enums.ResponseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException) throws IOException {

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .status(ResponseStatus.UNAUTHORIZED)
                .message("Unauthorized access - please login")
                .data(null)
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
    }
}
