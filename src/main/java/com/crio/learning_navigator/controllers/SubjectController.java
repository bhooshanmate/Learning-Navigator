package com.crio.learning_navigator.controllers;

import com.crio.learning_navigator.DTOs.SubjectRequestDTO;
import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.entities.Subject;
import com.crio.learning_navigator.exceptions.StudentNotEnrolledForSubjectException;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import com.crio.learning_navigator.exceptions.SubjectNotFoundException;
import com.crio.learning_navigator.services.StudentSubjectEnrollementService;
import com.crio.learning_navigator.services.SubjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {
    private final SubjectServices subjectServices;
    private final StudentSubjectEnrollementService studentSubjectEnrollementService;

    @Autowired
    public SubjectController(SubjectServices subjectServices,
                             StudentSubjectEnrollementService
                                     studentSubjectEnrollementService) {
        this.subjectServices = subjectServices;
        this.studentSubjectEnrollementService = studentSubjectEnrollementService;
    }

    @GetMapping
    public ResponseEntity<?> getAllSubjects() {
        List<Subject> allSubject = subjectServices.getAllSubject();
        return ResponseEntity.status(HttpStatus.OK).body(allSubject);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable("id") Long id)
            throws SubjectNotFoundException {
        Subject subjectById = subjectServices.getSubjectById(id);
        return ResponseEntity.status(HttpStatus.OK).body(subjectById);
    }
    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody SubjectRequestDTO
                                                       subjectRequestDTO){
        Subject subject = subjectServices.createSubject(subjectRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }
    @PutMapping("/enroll_students_to_subject/{studentId}/{subjectId}")
    public ResponseEntity<?> enrollStudentsToSubject(@PathVariable("studentId")Long studentId,
                                                     @PathVariable("subjectId") Long subjecId)
            throws SubjectNotFoundException, StudentNotFoundException {
        Student student = studentSubjectEnrollementService
                .enroll_student_to_subject(studentId, subjecId);
    /*
    Sending Student in the body to make sure Student rather than the Subject
     */
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PutMapping("/un_enroll_students_to_subject/{studentId}/{subjectId}")
    public ResponseEntity<?> unEnrollStudentsToSubject(@PathVariable("studentId")Long studentId,
                                                     @PathVariable("subjectId") Long subjecId)
            throws SubjectNotFoundException, StudentNotFoundException,
            StudentNotEnrolledForSubjectException {
        Student student = studentSubjectEnrollementService
                .un_enroll_student_from_subject(studentId,subjecId);
    /*
    Sending Student in the body to make sure Student rather than the Subject
     */
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") Long id)
            throws SubjectNotFoundException {
        Subject subject = subjectServices.deleteSubjectById(id);
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }
}
