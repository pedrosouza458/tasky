package com.template.tasky.dtos;

import java.util.Date;

public record UpdateTaskDTO( String title, String description, Date limitDate, Boolean done){}