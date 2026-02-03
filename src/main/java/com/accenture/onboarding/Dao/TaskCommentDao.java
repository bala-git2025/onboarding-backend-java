package com.accenture.onboarding.Dao;

import com.accenture.onboarding.model.TaskCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCommentDao extends JpaRepository<TaskCommentEntity, Long> {

    List<TaskCommentEntity> findByEmployeeTaskId(Long employeeTaskId);
}