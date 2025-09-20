package com.offcl.competency_tracker.mapper;

import com.offcl.competency_tracker.dto.quarter.QuarterRequestDto;
import com.offcl.competency_tracker.dto.quarter.QuarterResponseDto;
import com.offcl.competency_tracker.model.Quarter;

public class QuarterMapper {

    public static Quarter toEntity(QuarterRequestDto dto) {
        return Quarter.builder()
                .quarterName(dto.getQuarterName())
                .description(dto.getDescription())
                .fromDate(dto.getFromDate())
                .toDate(dto.getToDate())
                .status(dto.getStatus())
                .build();
    }

    public static QuarterResponseDto toResponse(Quarter entity) {
        return QuarterResponseDto.builder()
                .id(entity.getId())
                .quarterName(entity.getQuarterName())
                .description(entity.getDescription())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .status(entity.getStatus())
                .createdOn(entity.getCreatedOn())
                .updatedOn(entity.getUpdatedOn())
                .build();
    }
}
