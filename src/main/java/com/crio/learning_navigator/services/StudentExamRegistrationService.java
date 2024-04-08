package com.crio.learning_navigator.services;

import com.crio.learning_navigator.entities.Exam;
import com.crio.learning_navigator.entities.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentExamRegistrationService {
    public Student registerStudentForExam(Student student, Exam exam);
    public Student removeStudentFromExam(Student student, Exam exam);
}
