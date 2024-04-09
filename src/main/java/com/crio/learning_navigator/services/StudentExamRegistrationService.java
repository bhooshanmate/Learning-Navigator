package com.crio.learning_navigator.services;

import com.crio.learning_navigator.entities.Exam;
import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.exceptions.ExamNotFoundException;
import com.crio.learning_navigator.exceptions.StudentNotEnrolledForSubjectException;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface StudentExamRegistrationService {
    public Student register_student_for_exam(Long studentId,Long examId) throws StudentNotFoundException, ExamNotFoundException, StudentNotEnrolledForSubjectException;
    public Student remove_student_from_exam(Long studentId,Long examId) throws StudentNotFoundException, ExamNotFoundException, StudentNotEnrolledForSubjectException;
}
