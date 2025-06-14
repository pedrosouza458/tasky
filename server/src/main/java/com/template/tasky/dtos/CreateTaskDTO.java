package com.template.tasky.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTaskDTO(
        @NotNull String name,
        String description,
        LocalDateTime limitDate,
        Boolean done
) {
}
