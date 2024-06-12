package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PriorityName name;

    @Column(nullable = false)
    private String description;

    @OneToMany
    private List<Task> tasks;

    public Priority() {
    }

    public PriorityName getName() {
        return name;
    }

    public void setName(PriorityName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
