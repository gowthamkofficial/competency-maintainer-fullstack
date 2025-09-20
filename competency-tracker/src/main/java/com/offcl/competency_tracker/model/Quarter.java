package com.offcl.competency_tracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.offcl.competency_tracker.common.enums.QuarterStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Quarter extends BaseEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true,nullable = false)
	private String quarterName;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private LocalDate fromDate;
	
	@Column(nullable = false)
	private LocalDate toDate;
	
	@Enumerated(EnumType.STRING)
	private QuarterStatus status;
	
	@JsonIgnore()
	@OneToMany(mappedBy = "quarter",cascade = CascadeType.ALL)
	List<Course> courses =  new ArrayList<Course>();
		
}
