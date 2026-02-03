package com.accenture.onboarding.controller;

import com.accenture.onboarding.Service.EmployeeService;
import com.accenture.onboarding.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") 
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get Employee Details
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Map<String, EmployeeDTO>> getEmployeeDetails(@PathVariable Long employeeId) {
        EmployeeDTO employee = employeeService.getEmployeeDetails(employeeId);
        return wrapResponse("employee", employee);
    }

    // Get Employee Tasks
    @GetMapping("/employees/{employeeId}/tasks")
    public ResponseEntity<Map<String, List<TaskDTO>>> getEmployeeTasks(@PathVariable Long employeeId) {
        List<TaskDTO> tasks = employeeService.getEmployeeTasks(employeeId);
        return wrapResponse("tasks", tasks);
    }

    // Get Task Details
    @GetMapping("/employees/{employeeId}/tasks/{taskId}")
    public ResponseEntity<Map<String, TaskDetailDTO>> getTaskDetail(
            @PathVariable Long employeeId, 
            @PathVariable Long taskId) {
        TaskDetailDTO task = employeeService.getTaskDetail(employeeId, taskId);
        return wrapResponse("task", task);
    }

    // Get Comments
    @GetMapping("/taskComments/{taskId}")
    public ResponseEntity<Map<String, List<CommentDTO>>> getTaskComments(@PathVariable Long taskId) {
        List<CommentDTO> comments = employeeService.getTaskDetail(0L, taskId).getComments();
        return wrapResponse("taskComments", comments);
    }

    // Update Status
    @PutMapping("/employees/{employeeId}/tasks/{taskId}")
    public ResponseEntity<Map<String, TaskDetailDTO>> updateTaskStatus(
            @PathVariable Long employeeId, 
            @PathVariable Long taskId, 
            @RequestBody Map<String, String> request) {
        String status = request.get("status");
        TaskDetailDTO updatedTask = employeeService.updateTaskStatus(employeeId, taskId, status);
        return wrapResponse("task", updatedTask);
    }

    // Add Comment
    @PostMapping("/employees/{employeeId}/tasks/{taskId}/comments")
    public ResponseEntity<Void> addComment(
            @PathVariable Long employeeId, 
            @PathVariable Long taskId, 
            @RequestBody Map<String, String> request) {
        String commentText = request.get("comment");
        employeeService.addTaskComment(employeeId, taskId, commentText);
        return ResponseEntity.ok().build();
    }

    private <T> ResponseEntity<Map<String, T>> wrapResponse(String key, T data) {
        Map<String, T> response = new HashMap<>();
        response.put(key, data);
        return ResponseEntity.ok(response);
    }
}