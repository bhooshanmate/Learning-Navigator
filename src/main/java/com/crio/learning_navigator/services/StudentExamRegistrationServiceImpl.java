package com.crio.learning_navigator.services;

import com.crio.learning_navigator.entities.Exam;
import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.entities.Subject;
import com.crio.learning_navigator.exceptions.ExamNotFoundException;
import com.crio.learning_navigator.exceptions.StudentNotEnrolledForSubjectException;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentExamRegistrationServiceImpl implements StudentExamRegistrationService {
    private static final Logger log = LoggerFactory.getLogger(StudentExamRegistrationServiceImpl.class);
    private final StudentServices studentServices;
    private final ExamServices examServices;

    @Autowired
    public StudentExamRegistrationServiceImpl(StudentServices studentServices,
                                              ExamServices examServices) {
        this.studentServices = studentServices;
        this.examServices = examServices;
    }


    @Override
    public Student register_student_for_exam(Long studentId, Long examId)
            throws StudentNotFoundException, ExamNotFoundException,
            StudentNotEnrolledForSubjectException {

        Student foundStudent = studentServices.getStudentById(studentId);
        Exam foundExam = examServices.findExamById(examId);
        Subject examSubject = foundExam.getSubject();
        /*
        checking if student is enrolled in the respective subject
         */
        if (foundStudent.getEnrolledSubjects().contains(examSubject)){
            foundExam.getEnrolledStudents().add(foundStudent);
            foundStudent.getRegisteredExams().add(foundExam);
            Student student = studentServices.updateStudent(foundStudent);
            Exam exam = examServices.udpateExam(foundExam);
            log.info("Student Registered For Exam [{}]",
                    foundStudent.getStudentName()
                    + "-->" + foundExam.getId() + examId
            );
            return foundStudent;
        }
        else {
            log.error("Student {} NOT Enrolled for Subject {}",
                    foundStudent.getStudentName(),
                    examSubject.getSubjectName());
            throw new StudentNotEnrolledForSubjectException(
                    "Student NOT Enrolled For Subject"
            );
        }
    }

    @Override
    public Student remove_student_from_exam(Long studentId, Long examId) throws
            StudentNotFoundException, ExamNotFoundException,
            StudentNotEnrolledForSubjectException {
        Student foundStudent = studentServices.getStudentById(studentId);
        Exam foundExam = examServices.findExamById(examId);
        Subject examSubject = foundExam.getSubject();
        /*
            Here we can presume that student is enrolled in the exam subject
            hence, directly checking if this student is registered in the exam
         */
        if (foundExam.getEnrolledStudents().contains(foundStudent)) {

            foundExam.getEnrolledStudents().remove(foundStudent);
            foundStudent.getRegisteredExams().remove(foundExam);
            Student student = studentServices.updateStudent(foundStudent);
            Exam exam = examServices.udpateExam(foundExam);
            log.info("Removed Student {} From Exam",student.getStudentName());
            return foundStudent;

        }
        else {
            log.error("Student {} NOT Registered For Exam {}",
                    foundStudent.getStudentName(),
                    "EXAM ID : ",
                    foundExam.getId());
            throw new StudentNotEnrolledForSubjectException(
                    "Student NOT Enrolled For Subject"
            );
        }
    }
}
