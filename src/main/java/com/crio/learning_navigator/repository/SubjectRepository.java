package com.crio.learning_navigator.repository;

import com.crio.learning_navigator.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
