package com.template.tasky.controllers;

import com.template.tasky.models.User;
import com.template.tasky.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
