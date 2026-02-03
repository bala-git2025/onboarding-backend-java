package com.accenture.onboarding.dto;
import java.time.LocalDate;

public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private String status;
    private LocalDate createdOn;
    private String POC;
    private Long employeeTaskId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	public String getPOC() {
		return POC;
	}
	public void setPOC(String pOC) {
		POC = pOC;
	}
	public Long getEmployeeTaskId() {
		return employeeTaskId;
	}
	public void setEmployeeTaskId(Long employeeTaskId) {
		this.employeeTaskId = employeeTaskId;
	}

}