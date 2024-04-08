package com.crio.learning_navigator.DTOs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ExamRequestDTO {
    private SubjectRequestDTO subjectRequestDTO;

    public ExamRequestDTO(SubjectRequestDTO subjectRequestDTO) {
        this.subjectRequestDTO = subjectRequestDTO;
    }

    public ExamRequestDTO() {
    }

    public SubjectRequestDTO getSubjectRequestDTO() {
        return subjectRequestDTO;
    }

    public void setSubjectRequestDTO(SubjectRequestDTO subjectRequestDTO) {
        this.subjectRequestDTO = subjectRequestDTO;
    }
}
