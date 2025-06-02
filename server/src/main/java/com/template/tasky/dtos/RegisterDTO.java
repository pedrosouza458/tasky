package com.template.tasky.dtos;

import com.template.tasky.models.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role){
}
