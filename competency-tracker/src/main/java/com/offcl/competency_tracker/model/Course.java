package com.offcl.competency_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.offcl.competency_tracker.common.enums.CourseLevel;
import com.offcl.competency_tracker.common.enums.CourseStatus;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String courseName;
    
    @Column(nullable = false)
    private String courseDescription;
    
    @Column(nullable = false)
    private Integer courseDurationInHours;
    
    @Column(nullable = false)
    private Boolean paidCourse;
    
    @Column(nullable = true)
    private Double amountPaid;
    
    @Column(nullable = false)
    private LocalDate courseStartedOn;
    
    private LocalDate courseCompletedOn;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
   private CourseStatus status = CourseStatus.NOT_YET_STARTED;;
    
//    @Lob
//    private byte[] certificateCopy; // Assuming file is stored as a byte array
//    
    
    private String rejectionComment;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private CourseLevel level;
    
    
    @ManyToOne()
    @JoinColumn(name="quarter_id")
   private Quarter quarter;
    
    
    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

 
}


