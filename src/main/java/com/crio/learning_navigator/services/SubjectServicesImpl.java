package com.crio.learning_navigator.services;

import com.crio.learning_navigator.DTOs.SubjectRequestDTO;
import com.crio.learning_navigator.entities.Subject;
import com.crio.learning_navigator.exceptions.SubjectNotFoundException;
import com.crio.learning_navigator.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubjectServicesImpl implements SubjectServices{

    private static final Logger log = LoggerFactory.getLogger(SubjectServicesImpl.class);
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServicesImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject createSubject(SubjectRequestDTO subjectRequestDTO) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectRequestDTO.getSubjectName());
        log.info("New Subject Created By Subject ID : {}, Subject Name {}",
                subject.getId(),
                subject.getSubjectName());
        subjectRepository.save(subject);
        return subject;
    }

    @Override
    public Subject getSubjectById(Long id) throws SubjectNotFoundException {
        Optional<Subject> subjectById = subjectRepository.findById(id);
        if (subjectById.isPresent()) {
            log.info("Subject Found By ID : {}, Subject Name : {}",
                    subjectById.get().getId(),
                    subjectById.get().getSubjectName());
            return subjectById.get();
        }
        else {
            log.error("Subject Not Found By Id : {}",id);
            throw new SubjectNotFoundException("subject not found !");
        }
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject deleteSubjectById(Long id) throws SubjectNotFoundException {
        Subject subjectById = getSubjectById(id);
        subjectRepository.delete(subjectById);
        log.info("Subject Deleted By ID : {}, Subject Name : {}",
                id,
                subjectById.getSubjectName());
        return subjectById;
    }
    public Subject updateSubject(Subject subject) {
        Subject updatedSubject = subjectRepository.save(subject);
        return updatedSubject;
    }
}
