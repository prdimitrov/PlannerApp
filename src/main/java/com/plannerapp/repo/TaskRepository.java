package com.plannerapp.repo;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.viewModels.TaskViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserIsNull();

    List<Task> findAllByUser_Username(String username);
}
