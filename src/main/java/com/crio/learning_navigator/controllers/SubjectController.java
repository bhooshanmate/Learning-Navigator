package com.crio.learning_navigator.controllers;

import com.crio.learning_navigator.DTOs.SubjectRequestDTO;
import com.crio.learning_navigator.entities.Subject;
import com.crio.learning_navigator.exceptions.SubjectNotFoundException;
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

    @Autowired
    public SubjectController(SubjectServices subjectServices) {
        this.subjectServices = subjectServices;
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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") Long id)
            throws SubjectNotFoundException {
        Subject subject = subjectServices.deleteSubjectById(id);
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }
}
