package com.template.spring_template.controllers;

import com.template.spring_template.models.Task;
import com.template.spring_template.repositories.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam(defaultValue = "1") int page){
        Pageable pageable = PageRequest.of(page - 1, 20);
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return taskPage.getContent();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable UUID id){
        return taskRepository.findById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }
}
