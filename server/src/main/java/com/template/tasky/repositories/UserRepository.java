package com.template.tasky.repositories;

import com.template.tasky.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Task, UUID> {

}
