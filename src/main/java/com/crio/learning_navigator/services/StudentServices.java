package com.crio.learning_navigator.services;

import com.crio.learning_navigator.DTOs.StudentRequestDTO;
import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.exceptions.DuplicateStudentException;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentServices {
    public Student createStudent(StudentRequestDTO studentRequestDTO);
    public Student getStudentById(Long id) throws StudentNotFoundException;
    public List<Student> getAllStudents();
    public Student updateStudentNameById(Long id, StudentRequestDTO studentRequestDTO) throws StudentNotFoundException;
    public Student deleteStudentById(Long id) throws StudentNotFoundException;
    public Student updateStudent(Student student);
}
