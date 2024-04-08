package com.crio.learning_navigator.services;

import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.entities.Subject;
import com.crio.learning_navigator.exceptions.StudentNotEnrolledForSubjectException;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import com.crio.learning_navigator.exceptions.SubjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentSubjectEnrollementServiceImpl implements StudentSubjectEnrollementService{
    private static final Logger log = LoggerFactory.getLogger(StudentSubjectEnrollementServiceImpl.class);
    private final SubjectServices subjectServices;
    private final StudentServices studentServices;
    @Autowired
    public StudentSubjectEnrollementServiceImpl(SubjectServices subjectServices,
                                                StudentServices studentServices) {
        this.subjectServices = subjectServices;
        this.studentServices = studentServices;
    }

    @Override
    public Student enroll_student_to_subject(Long studentId, Long subjectId)
            throws SubjectNotFoundException, StudentNotFoundException {
        Subject foundSubject = subjectServices.getSubjectById(subjectId);
        Student foundStudent = studentServices.getStudentById(studentId);

        if (!foundSubject.getRegisteredStudents().contains(foundStudent)){
            foundSubject.getRegisteredStudents().add(foundStudent);
            foundStudent.getEnrolledSubjects().add(foundSubject);
            Student updatedStudent = studentServices
                    .updateStudent(foundStudent);
            Subject updatedSubject = subjectServices
                    .updateSubject(foundSubject);
            log.info("Successfully Enrolled Student [{}] to Subject [{}]",
                    updatedStudent.getStudentName(),
                    updatedSubject.getSubjectName());
            return updatedStudent;
        }
        else {
            log.error("Student [{}] Is Already Enrolled With Subject [{}]",
                    foundStudent.getStudentName(),
                    foundSubject.getSubjectName());
            return foundStudent;
        }
    }

    @Override
    public Student un_enroll_student_from_subject(Long studentId, Long subjectId)
            throws StudentNotFoundException, SubjectNotFoundException,
            StudentNotEnrolledForSubjectException {
        Student foundStudent = studentServices.getStudentById(studentId);
        Subject foundSubject = subjectServices.getSubjectById(subjectId);
        if (foundSubject.getRegisteredStudents().contains(foundStudent)) {
            foundSubject.getRegisteredStudents().remove(foundStudent);
            foundStudent.getEnrolledSubjects().remove(foundSubject);

            Student updatedStudent = studentServices
                    .updateStudent(foundStudent);
            Subject updatedSubject = subjectServices
                    .updateSubject(foundSubject);
            log.info("Successfully Un-enrolled Student [{}] to Subject [{}]",
                    updatedStudent.getStudentName(),
                    updatedSubject.getSubjectName());

            return updatedStudent;
        }
        else {
            log.info("Student [{}] Is Not Enrolled For Subject [{}]",
                    foundStudent.getStudentName(),
                    foundSubject.getSubjectName());
            throw new StudentNotEnrolledForSubjectException(
                    "Student Not Enrolled For This Subject"
            );
        }
    }
}
