package com.project.taskit.repositories;

import com.project.taskit.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;

interface  ActionRepository extends JpaRepository<Action, Long> {
}
