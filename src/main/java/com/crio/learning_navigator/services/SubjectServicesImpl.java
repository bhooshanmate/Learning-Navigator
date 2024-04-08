package com.crio.learning_navigator.services;

import com.crio.learning_navigator.DTOs.SubjectRequestDTO;
import com.crio.learning_navigator.entities.Subject;
import com.crio.learning_navigator.exceptions.SubjectNotFoundException;
import com.crio.learning_navigator.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServicesImpl implements SubjectServices{

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServicesImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject createSubject(SubjectRequestDTO subjectRequestDTO) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectRequestDTO.getSubjectName());
        return subject;
    }

    @Override
    public Subject getSubjectById(Long id) throws SubjectNotFoundException {
        Optional<Subject> subjectById = subjectRepository.findById(id);
        if (subjectById.isPresent()) {
            return subjectById.get();
        }
        else throw new SubjectNotFoundException("subject not found !");
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject updateSubjectById(Long id) {
        return null;
    }

    @Override
    public Subject deleteSubjectById(Long id) throws SubjectNotFoundException {
        Subject subjectById = getSubjectById(id);
        subjectRepository.delete(subjectById);
        return subjectById;
    }
}
