package com.offcl.competency_tracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class User extends BaseEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String empcode;
	
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
	
	@Column(nullable = false)
	private String password;
	
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Course> courses = new ArrayList<Course>();
	
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "profile_image_id")
	private FileEntity profileImage;
	
	
	
//	Security Methods 
	
	  @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.getRole()));
	    }
	  

	    @Override
	    public String getPassword() {
	      return password;
	    }

	    @Override
	    public String getUsername() {
	      return email;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	      return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	      return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	      return true;
	    }

	    @Override
	    public boolean isEnabled() {
	      return true;
	    }
	
}
