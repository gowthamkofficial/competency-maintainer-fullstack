package com.offcl.competency_tracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false,unique = true)
	private String email;
	
	@Column(nullable = false,unique = true)
	private String mobileNumber;
	
	@Column(nullable = false)
	private LocalDate dateOfJoining;
	
	@Column(nullable = false)
	private String addressLine1;
	
	@Column(nullable = true)
	private String addressLine2;
	
	@ManyToOne()
	@JoinColumn(name = "state_id")
	private State state;
	
	@ManyToOne()
	@JoinColumn(name = "district_id")
	private District district;
	
	
	@ManyToOne()
	@JoinColumn(name="role_id")
	private Role role;
	
	
	@ManyToOne()
	@JoinColumn(name="department_id")
	private Department department;
	
	@OneToMany(mappedBy = "user")
	private List<Course> courses = new ArrayList<Course>();
	
}
