package com.plannerapp.model.viewModels;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.User;

import java.time.LocalDate;

public class TaskViewModel {
    private Long id;
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private User user;

    public TaskViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
