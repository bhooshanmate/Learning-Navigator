package com.crio.learning_navigator.controllers;

import com.crio.learning_navigator.DTOs.StudentRequestDTO;
import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import com.crio.learning_navigator.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentServices studentServices;

    @Autowired
    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id){
        try {
            Student studentById = studentServices.getStudentById(id);
            return new ResponseEntity<>(studentById, HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(studentServices.getAllStudents(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        Student student = studentServices.createStudent(studentRequestDTO);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
}
