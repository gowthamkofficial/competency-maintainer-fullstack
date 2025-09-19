package com.offcl.competency_tracker.common.response;

import com.offcl.competency_tracker.common.enums.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T> {
	private ResponseStatus status;
	private String message;
	private T data;
}
