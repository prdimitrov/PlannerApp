package com.plannerapp.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    @Column(nullable = false)
    private String description;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;


    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Priority priority;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User user;

    public Task() {
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
