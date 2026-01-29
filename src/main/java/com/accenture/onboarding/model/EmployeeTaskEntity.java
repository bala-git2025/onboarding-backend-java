package com.accenture.onboarding.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_task")
@Data
@NoArgsConstructor
public class EmployeeTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskId", nullable = false)
    private TaskEntity task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", nullable = false)
    private User employee;

    private String status;

    @Column(name = "poc")
    private String pointOfContact;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "createdOn", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "updatedOn")
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    @JsonManagedReference("assignment-comments")
    @OneToMany(mappedBy = "employeeTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskCommentEntity> comments = new ArrayList<>();
}