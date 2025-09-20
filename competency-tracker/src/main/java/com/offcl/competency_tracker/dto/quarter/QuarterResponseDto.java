package com.offcl.competency_tracker.dto.quarter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.offcl.competency_tracker.common.enums.QuarterStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuarterResponseDto {
    private Long id;
    private String quarterName;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private QuarterStatus status;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
