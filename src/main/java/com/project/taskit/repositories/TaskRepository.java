package com.project.taskit.repositories;

import com.project.taskit.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
    void deleteById(Long id);
}
