package com.template.tasky.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetTaskByIdDTO(UUID id, String title, String description, LocalDateTime limitDate, Boolean done) {
}
