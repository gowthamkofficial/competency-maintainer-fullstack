package com.offcl.competency_tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.offcl.competency_tracker.common.response.ApiResponse;
import com.offcl.competency_tracker.dto.DistrictResponseDto;
import com.offcl.competency_tracker.dto.StateDistrictRequestDto;
import com.offcl.competency_tracker.dto.StateResponseDto;
import com.offcl.competency_tracker.dto.deparment.DepartmentRequestDto;
import com.offcl.competency_tracker.dto.deparment.DepartmentResponseDto;
import com.offcl.competency_tracker.dto.role.RoleRequestDto;
import com.offcl.competency_tracker.dto.role.RoleResponseDto;
import com.offcl.competency_tracker.model.District;
import com.offcl.competency_tracker.model.State;
import com.offcl.competency_tracker.service.MasterService;

@RestController
@RequestMapping("/api/v1/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    /*** State + Districts ***/
//    @PostMapping("/import-states")
    public ResponseEntity<Map<State, List<District>>> importStateAndDistricts(
            @RequestBody StateDistrictRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.masterService.importStatesAndDistricts(dto));
    }

    @GetMapping("/states")
    public ResponseEntity<ApiResponse<List<StateResponseDto>>> getAllStates() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.masterService.getAllStates());
    }

    @GetMapping("/districts/{stateId}")
    public ResponseEntity<ApiResponse<List<DistrictResponseDto>>> getAllDistrictsByStateId(
            @PathVariable(name = "stateId") Long stateId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.masterService.getAllDistrictsByState(stateId));
    }



}
