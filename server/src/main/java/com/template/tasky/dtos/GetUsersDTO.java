package com.template.tasky.dtos;

import java.util.UUID;

public record GetUsersDTO(UUID id, String name, String email) {
}
