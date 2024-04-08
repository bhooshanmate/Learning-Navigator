package com.crio.learning_navigator.services;

import com.crio.learning_navigator.entities.Exam;
import com.crio.learning_navigator.entities.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentExamRegistrationServiceImpl implements StudentExamRegistrationService {
    @Override
    public Student registerStudentForExam(Student student, Exam exam) {
        student.getRegisteredExams().add(exam);
        exam.getEnrolledStudents().add(student);
        return student;
    }

    @Override
    public Student removeStudentFromExam(Student student, Exam exam) {
        student.getRegisteredExams().remove(exam);
        exam.getEnrolledStudents().remove(student);
        return student;
    }
}
