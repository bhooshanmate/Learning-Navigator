package com.crio.learning_navigator.services;

import com.crio.learning_navigator.DTOs.ExamRequestDTO;
import com.crio.learning_navigator.entities.Exam;
import com.crio.learning_navigator.entities.Subject;
import com.crio.learning_navigator.exceptions.ExamNotFoundException;
import com.crio.learning_navigator.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServicesImpl implements ExamServices {
    private final ExamRepository examRepository;
    private final SubjectServices subjectServices;

    @Autowired
    public ExamServicesImpl(ExamRepository examRepository, SubjectServices subjectServices) {
        this.examRepository = examRepository;
        this.subjectServices = subjectServices;
    }

    @Override
    public Exam createExam(ExamRequestDTO examRequestDTO) {
        Exam exam = new Exam();
        Subject subject = subjectServices.createSubject(examRequestDTO.getSubjectRequestDTO());
        exam.setSubject(subject);
        examRepository.save(exam);
        return exam;
    }

    @Override
    public Exam findExamById(Long id) throws ExamNotFoundException {
        Optional<Exam> examById = examRepository.findById(id);
        if (examById.isPresent()) {
            return examById.get();
        }
        else throw new ExamNotFoundException("exam not found");
    }

    @Override
    public List<Exam> findAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam deleteExamById(Long id) throws ExamNotFoundException {
        Exam examById = findExamById(id);
        examRepository.delete(examById);
        return examById;
    }
}
