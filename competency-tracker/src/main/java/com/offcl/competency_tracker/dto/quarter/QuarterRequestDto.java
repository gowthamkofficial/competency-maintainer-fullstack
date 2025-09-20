package com.offcl.competency_tracker.dto.quarter;

import java.time.LocalDate;

import com.offcl.competency_tracker.common.enums.QuarterStatus;

import lombok.Data;

@Data
public class QuarterRequestDto {
    private String quarterName;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private QuarterStatus status;
}
