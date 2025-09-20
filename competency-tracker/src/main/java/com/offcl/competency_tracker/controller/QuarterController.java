package com.offcl.competency_tracker.controller;

import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.common.response.PagedResponse;
import com.offcl.competency_tracker.dto.quarter.QuarterRequestDto;
import com.offcl.competency_tracker.dto.quarter.QuarterResponseDto;
import com.offcl.competency_tracker.service.QuarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quarters")
public class QuarterController {

    @Autowired
    private QuarterService quarterService;

    /*** Create quarter ***/
    @PostMapping("/createQuarter")
    public ResponseEntity<ApiResponse<QuarterResponseDto>> createQuarter(@RequestBody QuarterRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(quarterService.createQuarter(dto));
    }

    /*** Update quarter ***/
    @PutMapping("/updateQuarter/{id}")
    public ResponseEntity<ApiResponse<QuarterResponseDto>> updateQuarter(
            @PathVariable Long id,
            @RequestBody QuarterRequestDto dto) {
        return ResponseEntity.ok(quarterService.updateQuarter(id, dto));
    }

    /*** Get quarter by id ***/
    @GetMapping("/viewQuarter/{id}")
    public ResponseEntity<ApiResponse<QuarterResponseDto>> getQuarterById(@PathVariable Long id) {
        return ResponseEntity.ok(quarterService.getQuarterById(id));
    }

    /*** List all quarters with pagination ***/
    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<QuarterResponseDto>>> getAllQuarters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(quarterService.getAllQuarters(page, size));
    }
}
