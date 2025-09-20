package com.offcl.competency_tracker.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.offcl.competency_tracker.common.enums.CourseLevel;
import com.offcl.competency_tracker.common.enums.CourseStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "courses",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"quarter_id", "user_id", "course_name"})
    }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String courseDescription;

    @Column(nullable = false)
    private Integer courseDurationInHours;

    @Column(nullable = false)
    private Boolean paidCourse;

    private Double amountPaid;

    @Column(nullable = false)
    private LocalDate courseStartedOn;

    private LocalDate courseCompletedOn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus status = CourseStatus.NOT_YET_STARTED;

    private String rejectionComment;

    @Enumerated(EnumType.STRING)
    private CourseLevel level;

    @ManyToOne
    @JoinColumn(name = "quarter_id", nullable = false)
    private Quarter quarter;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "certificate_id")
    private FileEntity certificate;
}
