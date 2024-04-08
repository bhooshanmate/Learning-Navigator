package com.crio.learning_navigator.services;

import com.crio.learning_navigator.DTOs.StudentRequestDTO;
import com.crio.learning_navigator.entities.Student;
import com.crio.learning_navigator.exceptions.StudentNotFoundException;
import com.crio.learning_navigator.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServicesImpl implements StudentServices{
    private static final Logger log = LoggerFactory.getLogger(StudentServicesImpl.class);
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
        log.info("Student Created By Student ID : {}",student.getId());
        return student;
    }

    @Override
    public Student getStudentById(Long id) throws StudentNotFoundException {
        Optional<Student> studentById = studentRepository.findById(id);
        if (studentById.isPresent()) {
            log.info("Student Found By ID : {}",id);
            return studentById.get();
        }
        else {
            log.error("Student Not Found By ID : {}",id);
            throw new StudentNotFoundException("Student Not Found");
        }
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
    public Student updateStudentNameById(Long id, StudentRequestDTO studentRequestDTO) throws StudentNotFoundException {
        Student student = getStudentById(id);
        String previousName = student.getStudentName();
        student.setStudentName(studentRequestDTO.getName());
        log.info("Student Name Changed To {}, Student ID : {}",
                previousName + " ---> " + student.getStudentName(),
                student.getId());
        return student;
    }

    @Override
    public Student deleteStudentById(Long id) throws StudentNotFoundException {
        Student student = getStudentById(id);
        log.info("Student Got Deleted, Student ID : {}",student.getId());
        return student;
    }
    @Override
    public Student updateStudent(Student student) {
        Student updatedStudent = studentRepository.save(student);
        return updatedStudent;
    }
}
