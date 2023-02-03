package com.project.taskit.repositories;

import com.project.taskit.models.Category;
import com.project.taskit.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    void deleteById(Long id);
    List<Task> findAllByScheduledDate(String date);
    List<Task> findAllByCategory(Category category);
}
