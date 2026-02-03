package com.accenture.onboarding.dto;
import java.time.LocalDate;
import java.util.List;

public class TaskDetailDTO {
    private Long id;
    private String name;
    private String status;
    private LocalDate dueDate;
    private LocalDate createdOn;
    private String description;
    private String pointOfContact;
    private LocalDate completedOn;
    private List<CommentDTO> comments;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPointOfContact() {
		return pointOfContact;
	}
	public void setPointOfContact(String pointOfContact) {
		this.pointOfContact = pointOfContact;
	}
	public LocalDate getCompletedOn() {
		return completedOn;
	}
	public void setCompletedOn(LocalDate completedOn) {
		this.completedOn = completedOn;
	}
	public List<CommentDTO> getComments() {
		return comments;
	}
	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

}