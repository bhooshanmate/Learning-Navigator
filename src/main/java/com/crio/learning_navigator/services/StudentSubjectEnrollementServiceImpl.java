package com.crio.learning_navigator.services;

import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.entities.Subject;
import org.springframework.stereotype.Service;

@Service
public class StudentSubjectEnrollementServiceImpl implements StudentSubjectEnrollementService{

    @Override
    public Student enroll_student_to_subject(Student student, Subject subject) {
        student.getEnrolledSubjects().add(subject);
        subject.getRegisteredStudents().add(student);
        return student;
    }

    @Override
    public Student un_enroll_student_from_subject(Student student, Subject subject) {
        student.getEnrolledSubjects().remove(subject);
        subject.getRegisteredStudents().remove(student);
        return student;
    }
}
