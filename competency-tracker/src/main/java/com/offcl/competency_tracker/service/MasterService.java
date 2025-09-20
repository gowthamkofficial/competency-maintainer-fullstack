package com.offcl.competency_tracker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcl.competency_tracker.common.enums.ResponseStatus;
import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.dto.DistrictResponseDto;
import com.offcl.competency_tracker.dto.StateDistrictRequestDto;
import com.offcl.competency_tracker.dto.StateResponseDto;
import com.offcl.competency_tracker.dto.deparment.DepartmentRequestDto;
import com.offcl.competency_tracker.dto.deparment.DepartmentResponseDto;
import com.offcl.competency_tracker.dto.role.RoleRequestDto;
import com.offcl.competency_tracker.dto.role.RoleResponseDto;
import com.offcl.competency_tracker.exception.AlreadyExistsException;
import com.offcl.competency_tracker.exception.ResourceNotFoundException;
import com.offcl.competency_tracker.mapper.DepartmentMapper;
import com.offcl.competency_tracker.mapper.RoleMapper;
import com.offcl.competency_tracker.mapper.StateDistrictMapper;
import com.offcl.competency_tracker.model.Department;
import com.offcl.competency_tracker.model.District;
import com.offcl.competency_tracker.model.Role;
import com.offcl.competency_tracker.model.State;
import com.offcl.competency_tracker.repository.DepartmentRepository;
import com.offcl.competency_tracker.repository.DistrictRepository;
import com.offcl.competency_tracker.repository.RoleRepository;
import com.offcl.competency_tracker.repository.StateRepository;

@Service
public class MasterService {

	
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private DistrictRepository districtRepo;
	

	
	
	
	
/***States And Districts Import***/
	
	public Map<State, List<District>> importStatesAndDistricts(StateDistrictRequestDto dto) {
	    Map<State, List<District>> imported = new HashMap();

	    dto.getStates().forEach(s -> {
	        State savedState = stateRepo.save(State.builder().stateName(s.getState()).build());
	        List<District> savedDistricts = s.getDistricts().stream()
	                .map(d -> districtRepo.save(District.builder()
	                                                    .districtName(d)
	                                                    .state(savedState)
	                                                    .build())) .toList();
	        imported.put(savedState, savedDistricts);
	    });

	    return imported;
	}
	
	
	/***States List***/
	
	public  ApiResponse<List<StateResponseDto>>  getAllStates() {
		
		List<StateResponseDto> stateResponse =  this.stateRepo.findAll().stream().map(s->StateDistrictMapper.mapState(s)).toList();
		
		return new ApiResponse<List<StateResponseDto>>(ResponseStatus.Success,"Fetched all states successfully",stateResponse);
	}
	
	/***Districts List***/
	
	
	public ApiResponse<List<DistrictResponseDto>> getAllDistrictsByState(Long stateId) {
		
		List<DistrictResponseDto> districtResponse  =  this.districtRepo.findByStateId(stateId).stream().map(d->StateDistrictMapper.mapDistrict(d)).toList();
		return new ApiResponse<List<DistrictResponseDto>>(ResponseStatus.Success,"Fetched all districts successfully",districtResponse);
	}
	
	

    
    

	
	
}
