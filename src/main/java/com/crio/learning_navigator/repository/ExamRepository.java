package com.crio.learning_navigator.repository;

import com.crio.learning_navigator.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Long> {
}
