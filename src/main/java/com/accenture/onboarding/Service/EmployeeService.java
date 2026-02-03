package com.accenture.onboarding.Service;

import com.accenture.onboarding.dto.*;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO getEmployeeDetails(Long employeeId);
    List<TaskDTO> getEmployeeTasks(Long employeeId);
    TaskDetailDTO getTaskDetail(Long employeeId, Long taskId);
    TaskDetailDTO updateTaskStatus(Long employeeId, Long taskId, String status);
    void addTaskComment(Long employeeId, Long taskId, String commentText);
}