package com.accenture.onboarding.serviceImpl;

import com.accenture.onboarding.Dao.EmployeeDao;
import com.accenture.onboarding.Dao.EmployeeTaskDao;
import com.accenture.onboarding.Dao.TaskCommentDao;
import com.accenture.onboarding.Service.EmployeeService;
import com.accenture.onboarding.dto.*;
import com.accenture.onboarding.model.EmployeeEntity;
import com.accenture.onboarding.model.EmployeeTaskEntity;
import com.accenture.onboarding.model.TaskCommentEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final EmployeeTaskDao employeeTaskDao;
    private final TaskCommentDao taskCommentDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao, EmployeeTaskDao employeeTaskDao, TaskCommentDao taskCommentDao) {
        this.employeeDao = employeeDao;
        this.employeeTaskDao = employeeTaskDao;
        this.taskCommentDao = taskCommentDao;
    }

    @Override
    public EmployeeDTO getEmployeeDetails(Long employeeId) {
        EmployeeEntity employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setJoiningDate(employee.getJoiningDate());
        dto.setPrimarySkill(employee.getPrimarySkill());
        return dto;
    }

    @Override
    public List<TaskDTO> getEmployeeTasks(Long employeeId) {
        List<EmployeeTaskEntity> assignments = employeeTaskDao.findByEmployeeId(employeeId);

        return assignments.stream().map(assign -> {
            TaskDTO dto = new TaskDTO();
            dto.setId(assign.getId());
            dto.setEmployeeTaskId(assign.getId());

            if (assign.getTask() != null) {
                dto.setName(assign.getTask().getName());
                dto.setDescription(assign.getTask().getDescription());
            }

            dto.setStatus(assign.getStatus());
            dto.setDueDate(assign.getDueDate());
            
            if (assign.getCreatedOn() != null) {
                dto.setCreatedOn(assign.getCreatedOn().toLocalDate());
            }

            if (assign.getPoc() != null) {
                dto.setPOC(assign.getPoc().toString()); 
            } else {
                dto.setPOC("N/A");
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public TaskDetailDTO getTaskDetail(Long employeeId, Long taskId) {
        EmployeeTaskEntity assign = employeeTaskDao.findByIdAndEmployeeId(taskId, employeeId)
                .orElseThrow(() -> new RuntimeException("Task not found or access denied"));

        TaskDetailDTO dto = new TaskDetailDTO();
        dto.setId(assign.getId());
        
        if (assign.getTask() != null) {
            dto.setName(assign.getTask().getName());
            dto.setDescription(assign.getTask().getDescription());
        }

        dto.setStatus(assign.getStatus());
        dto.setDueDate(assign.getDueDate());
        
        if (assign.getCreatedOn() != null) {
            dto.setCreatedOn(assign.getCreatedOn().toLocalDate());
        }

        if (assign.getPoc() != null) {
            dto.setPointOfContact(assign.getPoc().toString());
        } else {
            dto.setPointOfContact("N/A");
        }

        List<CommentDTO> comments = assign.getComments().stream().map(c -> {
            CommentDTO cDto = new CommentDTO();
            cDto.setId(c.getId());
            cDto.setText(c.getComment());
            cDto.setAuthor(c.getCreatedBy());
            cDto.setTimestamp(c.getCreatedOn());
            return cDto;
        }).collect(Collectors.toList());

        dto.setComments(comments);
        return dto;
    }

    @Override
    @Transactional
    public TaskDetailDTO updateTaskStatus(Long employeeId, Long taskId, String status) {
        EmployeeTaskEntity assign = employeeTaskDao.findByIdAndEmployeeId(taskId, employeeId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        assign.setStatus(status);
        employeeTaskDao.save(assign);
        
        return getTaskDetail(employeeId, taskId);
    }

    @Override
    @Transactional
    public void addTaskComment(Long employeeId, Long taskId, String commentText) {
        EmployeeTaskEntity assign = employeeTaskDao.findByIdAndEmployeeId(taskId, employeeId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        TaskCommentEntity comment = new TaskCommentEntity();
        comment.setComment(commentText);
        comment.setCreatedBy("Current User"); 
        comment.setEmployeeTask(assign);
        
        taskCommentDao.save(comment);
    }
}