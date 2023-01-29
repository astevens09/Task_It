package com.project.taskit.repositories;

import com.project.taskit.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
     Category findByType(String type);

}
