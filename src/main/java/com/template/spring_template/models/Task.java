package com.template.spring_template.models;

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
}
