package com.plannerapp.service;

import com.plannerapp.model.bindingModels.AddTaskModel;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.viewModels.TaskViewModel;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final PriorityService priorityService;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper, CurrentUser currentUser, PriorityService priorityService, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.priorityService = priorityService;
        this.userRepository = userRepository;
    }

    public List<TaskViewModel> findAll() {
        return taskRepository.findAllByUserIsNull()
                .stream()
                .map(task -> modelMapper.map(task, TaskViewModel.class))
                .collect(Collectors.toList());
    }

    public List<TaskViewModel> getMyTasks() {
        return taskRepository.findAllByUser_Username(currentUser.getUsername())
                .stream()
                .map(task -> modelMapper.map(task, TaskViewModel.class))
                .collect(Collectors.toList());
    }

    public void addTask(AddTaskModel taskModel) {
        Task task = modelMapper.map(taskModel, Task.class);
        task.setPriority(priorityService.findByName(taskModel.getPriority()));
        taskRepository.saveAndFlush(task);
    }

    public void assignTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setUser(userRepository.findByUsername(currentUser.getUsername()).get());
        taskRepository.save(task);
    }

    public void removeTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void returnTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setUser(null);
        taskRepository.save(task);
    }
}
