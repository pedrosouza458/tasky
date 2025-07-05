package com.template.tasky.controllers;

import com.template.tasky.models.Task;
import com.template.tasky.dtos.UpdateTaskDTO;
import com.template.tasky.repositories.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@RequestParam(defaultValue = "1") int page){
        Pageable pageable = PageRequest.of(page - 1, 20);
        Page<Task> taskPage = taskRepository.findAll(pageable);
        List<Task> tasks = taskPage.getContent();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable UUID id){
        Optional<Task> result = taskRepository.findById(id);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody Task task){
        try {
            taskRepository.save(task);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable UUID id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task created successfully");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable UUID id, @RequestBody UpdateTaskDTO updateTaskDTO){
        Optional<Task> result = taskRepository.findById(id);

        if(result.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        try {
            Task taskToUpdate = result.get();
            taskToUpdate.updateTask(updateTaskDTO);
            taskRepository.save(taskToUpdate);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Task update sucessfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Failed to update task");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }
}
