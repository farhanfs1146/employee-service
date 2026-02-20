package com.example.employee_service.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "father_name", nullable = false, length = 150)
    private String fatherName;

    @Column(name = "mother_name", nullable = false, length = 150)
    private String motherName;

    @Column(name = "qualification_id", nullable = false)
    private Long qualificationId;

//    @Column(name = "is_active")
//    private Boolean isActive = true;

    // audit fields
    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private Integer updatedBy;
}

