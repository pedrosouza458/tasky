package com.template.tasky.models;

import com.template.tasky.dtos.UpdateTaskDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;

    @Column(name = "limit_date")
    private Date limitDate;

    private Boolean done = false;

    public void updateTask(UpdateTaskDTO updateTaskDTO){
        if(updateTaskDTO.title() != null){
            this.setTitle(updateTaskDTO.title());
        }
        if(updateTaskDTO.description() != null){
            this.setDescription(updateTaskDTO.description());
        }
        if(updateTaskDTO.done() != null){
            this.setDone(updateTaskDTO.done());
        }
        if(updateTaskDTO.limitDate() != null){
            this.setLimitDate(updateTaskDTO.limitDate());
        }
    }
}
