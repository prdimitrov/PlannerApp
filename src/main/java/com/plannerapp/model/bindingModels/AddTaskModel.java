package com.plannerapp.model.bindingModels;

import com.plannerapp.model.enums.PriorityName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddTaskModel {

    @NotNull
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @NotNull
    @Future(message = "Due date must be in future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull
    private PriorityName priority;

    public AddTaskModel() {
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

    public PriorityName getPriority() {
        return priority;
    }

    public void setPriority(PriorityName priority) {
        this.priority = priority;
    }
}
