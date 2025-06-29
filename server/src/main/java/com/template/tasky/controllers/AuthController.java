package com.template.tasky.controllers;

import com.template.tasky.dtos.LoginDTO;
import com.template.tasky.dtos.LoginResponseDTO;
import com.template.tasky.dtos.RegisterDTO;
import com.template.tasky.infra.security.TokenService;
import com.template.tasky.models.User;
import com.template.tasky.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data){
       var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
       var auth = this.authenticationManager.authenticate(usernamePassword);

       var token = tokenService.generateToken((User) auth.getPrincipal());

       return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
       if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

       String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
       User newUser = new User(data.name(), data.email(), encryptedPassword, data.role());

       this.userRepository.save(newUser);

       return ResponseEntity.ok().build();
    }
}
