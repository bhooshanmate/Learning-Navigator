package com.crio.learning_navigator.services;

import com.crio.learning_navigator.DTOs.StudentRequestDTO;
import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import com.crio.learning_navigator.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServicesImpl implements StudentServices{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServicesImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(StudentRequestDTO studentRequestDTO){
        Student student = new Student();
        student.setStudentName(studentRequestDTO.getName());
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student getStudentById(Long id) throws StudentNotFoundException {
        Optional<Student> studentById = studentRepository.findById(id);
        if (studentById.isPresent()) {
            return studentById.get();
        }
        else throw new StudentNotFoundException("student not found");
    }

    @Override
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    /*
    here only name of student can be changed,
    I have given other ways to play with enrolled
    subjects and exams :)
     */
    @Override
    public Student updateStudentById(Long id, StudentRequestDTO studentRequestDTO)
            throws StudentNotFoundException {
        Student student = getStudentById(id);
        student.setStudentName(studentRequestDTO.getName());
        return student;
    }

    @Override
    public Student deleteStudentById(Long id) throws StudentNotFoundException {
        Student student = getStudentById(id);
        return student;
    }
}
