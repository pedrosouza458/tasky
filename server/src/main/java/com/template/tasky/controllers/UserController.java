package com.template.tasky.controllers;

import com.template.tasky.dtos.UpdateTaskDTO;
import com.template.tasky.dtos.UpdateUserDTO;
import com.template.tasky.models.Task;
import com.template.tasky.models.User;
import com.template.tasky.repositories.UserRepository;
import com.template.tasky.services.AuthService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
   private final UserRepository userRepository;

   public UserController(UserRepository userRepository) {
       this.userRepository = userRepository;
   }

   @Autowired
   private AuthService authService;

   @GetMapping()
   public ResponseEntity<Object> getUsers(){
       List<User> result = userRepository.findAll();

       if(result.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       else {
           return ResponseEntity.status(HttpStatus.OK).body(result);
       }
   }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getUserById(@PathVariable UUID id){
      Optional<User> result = userRepository.findById(String.valueOf(id));

      if(result.isPresent()){
          return ResponseEntity.status(HttpStatus.OK).body(result.get());
      }
      else {
          Map<String, String> response = new HashMap<>();
          response.put("message", "User not found");
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
      }
  }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestBody UpdateUserDTO user){
       org.springframework.security.core.userdetails.User currentUser = authService.getLoggedUser();
        if(result.isPresent()){
            try {
                Task updateTask = result.get();

                if (task.title() != null) updateTask.setTitle(task.title());
                if (task.description() != null) updateTask.setDescription(task.description());
                if (task.limitDate() != null) updateTask.setLimitDate(task.limitDate());
                if (task.done() != null) updateTask.setDone(task.done());

                taskRepository.save(updateTask);

                Map<String, String> response = new HashMap<>();
                response.put("message", "Task update sucessfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } catch (RuntimeException e) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Failed to update task");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
