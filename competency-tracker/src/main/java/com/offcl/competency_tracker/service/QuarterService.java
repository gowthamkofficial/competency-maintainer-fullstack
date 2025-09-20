package com.offcl.competency_tracker.service;

import com.offcl.competency_tracker.common.enums.ResponseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.common.response.PagedResponse;
import com.offcl.competency_tracker.dto.quarter.QuarterRequestDto;
import com.offcl.competency_tracker.dto.quarter.QuarterResponseDto;
import com.offcl.competency_tracker.exception.AlreadyExistsException;
import com.offcl.competency_tracker.exception.ResourceNotFoundException;
import com.offcl.competency_tracker.mapper.QuarterMapper;
import com.offcl.competency_tracker.model.Quarter;
import com.offcl.competency_tracker.repository.QuarterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuarterService {

    @Autowired
    private QuarterRepository quarterRepo;

    /** Create */
    public ApiResponse<QuarterResponseDto> createQuarter(QuarterRequestDto dto) {
        if (quarterRepo.existsByQuarterName(dto.getQuarterName())) {
            throw new AlreadyExistsException("Quarter already exists: " + dto.getQuarterName());
        }

        Quarter quarter = QuarterMapper.toEntity(dto);
        Quarter saved = quarterRepo.save(quarter);

        return new ApiResponse<>(ResponseStatus.Success,
                "Quarter created successfully",
                QuarterMapper.toResponse(saved));
    }

    /** Update */
    public ApiResponse<QuarterResponseDto> updateQuarter(Long id, QuarterRequestDto dto) {
        Quarter quarter = quarterRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarter not found with id " + id));

        quarter.setQuarterName(dto.getQuarterName());
        quarter.setDescription(dto.getDescription());
        quarter.setFromDate(dto.getFromDate());
        quarter.setToDate(dto.getToDate());
        quarter.setStatus(dto.getStatus());

        Quarter updated = quarterRepo.save(quarter);

        return new ApiResponse<>(ResponseStatus.Success,
                "Quarter updated successfully",
                QuarterMapper.toResponse(updated));
    }

    /** Get by Id */
    public ApiResponse<QuarterResponseDto> getQuarterById(Long id) {
        Quarter quarter = quarterRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarter not found with id " + id));

        return new ApiResponse<>(ResponseStatus.Success,
                "Quarter fetched successfully",
                QuarterMapper.toResponse(quarter));
    }

    /** List with pagination */
    public ApiResponse<PagedResponse<QuarterResponseDto>> getAllQuarters(int page, int size) {
        Page<Quarter> quarters = quarterRepo.findAll(PageRequest.of(page, size));

        List<QuarterResponseDto> content = quarters.getContent()
                .stream()
                .map(QuarterMapper::toResponse)
                .toList();

        PagedResponse<QuarterResponseDto> pagedResponse = PagedResponse.<QuarterResponseDto>builder()
                .content(content)
                .page(quarters.getNumber())
                .size(quarters.getSize())
                .totalElements(quarters.getTotalElements())
                .totalPages(quarters.getTotalPages())
                .last(quarters.isLast())
                .build();

        return new ApiResponse<>(ResponseStatus.Success,
                "Quarters fetched successfully",
                pagedResponse);
    }
}
