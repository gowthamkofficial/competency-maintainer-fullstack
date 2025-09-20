package com.offcl.competency_tracker.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TimestampDto {

	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
}
