package com.offcl.competency_tracker.mapper;

import com.offcl.competency_tracker.dto.DistrictResponseDto;
import com.offcl.competency_tracker.dto.StateResponseDto;
import com.offcl.competency_tracker.model.District;
import com.offcl.competency_tracker.model.State;

public class StateDistrictMapper {

	
	public static StateResponseDto mapState(State state) {
		
		return StateResponseDto.builder()
				.state(state.getStateName())
				.stateId(state.getId())
				.build();
	}
	
	
	public static DistrictResponseDto mapDistrict(District district) {
		
		return DistrictResponseDto.builder()
				.district(district.getDistrictName())
				.districtId(district.getId())
				.state(district.getState().getStateName())
				.stateId(district.getState().getId())
				.build();
				
	}
}
