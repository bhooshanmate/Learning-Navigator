package com.crio.learning_navigator.services;

import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.entities.Subject;
import org.springframework.stereotype.Service;

@Service
public interface StudentSubjectEnrollementService {
    public Student enroll_student_to_subject(Student student ,Subject subject);
    public Student un_enroll_student_from_subject(Student student,Subject subject);
}
