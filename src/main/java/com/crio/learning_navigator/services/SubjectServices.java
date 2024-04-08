package com.crio.learning_navigator.services;

import com.crio.learning_navigator.DTOs.SubjectRequestDTO;
import com.crio.learning_navigator.entities.Subject;
import com.crio.learning_navigator.exceptions.SubjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectServices {
    public Subject createSubject(SubjectRequestDTO subjectRequestDTO);
    public Subject getSubjectById(Long id) throws SubjectNotFoundException;
    public List<Subject> getAllSubject();
    public Subject updateSubjectById(Long id);
    public Subject deleteSubjectById(Long id) throws SubjectNotFoundException;
}
