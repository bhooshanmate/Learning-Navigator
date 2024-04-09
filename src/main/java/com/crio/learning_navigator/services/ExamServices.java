package com.crio.learning_navigator.services;

import com.crio.learning_navigator.DTOs.ExamRequestDTO;
import com.crio.learning_navigator.entities.Exam;
import com.crio.learning_navigator.exceptions.ExamNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamServices {
    public Exam createExam(ExamRequestDTO examRequestDTO);
    public Exam findExamById(Long id) throws ExamNotFoundException;
    public List<Exam> findAllExams() ;
    public Exam deleteExamById(Long id) throws ExamNotFoundException;
    public Exam udpateExam(Exam exam);
}
