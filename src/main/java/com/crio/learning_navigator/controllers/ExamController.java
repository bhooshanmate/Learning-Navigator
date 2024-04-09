package com.crio.learning_navigator.controllers;

import com.crio.learning_navigator.DTOs.ExamRequestDTO;
import com.crio.learning_navigator.entities.Exam;
import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.exceptions.ExamNotFoundException;
import com.crio.learning_navigator.exceptions.StudentNotEnrolledForSubjectException;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import com.crio.learning_navigator.services.ExamServices;
import com.crio.learning_navigator.services.StudentExamRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {
    private final ExamServices examServices;
    private final StudentExamRegistrationService studentExamRegistrationService;

    @Autowired
    public ExamController(ExamServices examServices,
                          StudentExamRegistrationService studentExamRegistrationService) {
        this.examServices = examServices;
        this.studentExamRegistrationService = studentExamRegistrationService;
    }

    @PostMapping
    public ResponseEntity<?> createExam(@RequestBody ExamRequestDTO examRequestDTO){
        Exam exam = examServices.createExam(examRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(exam);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExamById(@PathVariable("id") Long id) throws
            ExamNotFoundException {
        Exam examById = examServices.findExamById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(examById);
    }

    @GetMapping
    public ResponseEntity<?> getAllExams() {
        List<Exam> allExams = examServices.findAllExams();
        return ResponseEntity.status(HttpStatus.OK).body(allExams);
    }

    @PutMapping("/register_student_for_exam/{studentId}/{examId}")
    public ResponseEntity<?> registerStudentForExam(@PathVariable("studentId") Long studentId,
                                                    @PathVariable("examId") Long examId) throws
            StudentNotEnrolledForSubjectException, StudentNotFoundException, ExamNotFoundException {
        Student student = studentExamRegistrationService.register_student_for_exam(studentId, examId);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PutMapping("/remove_student_from_exam/{studentId}/{examId}")
    public ResponseEntity<?> removeStudentFromExam(@PathVariable("studentId") Long studentId,
                                                   @PathVariable("examId") Long examId) throws
            StudentNotEnrolledForSubjectException, StudentNotFoundException, ExamNotFoundException {
        Student student = studentExamRegistrationService.remove_student_from_exam(studentId, examId);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExamById(@PathVariable("id") Long id) throws
            ExamNotFoundException {
        Exam exam = examServices.deleteExamById(id);
        return ResponseEntity.status(HttpStatus.OK).body(exam);
    }
}
