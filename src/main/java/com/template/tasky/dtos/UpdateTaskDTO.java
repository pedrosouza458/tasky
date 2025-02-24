package com.template.tasky.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDTO {
    private String title;
    private String description;
    private Date limitDate;
    private Boolean done;
}
