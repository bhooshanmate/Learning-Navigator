package com.crio.learning_navigator.services;

import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.entities.Subject;
import com.crio.learning_navigator.exceptions.StudentNotEnrolledForSubjectException;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import com.crio.learning_navigator.exceptions.SubjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface StudentSubjectEnrollementService {
    public Student enroll_student_to_subject(Long studentId,Long subjectId) throws SubjectNotFoundException, StudentNotFoundException;
    public Student un_enroll_student_from_subject(Long studentId, Long subjectId) throws StudentNotFoundException, SubjectNotFoundException, StudentNotEnrolledForSubjectException;
}
