package com.crio.learning_navigator.DTOs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class SubjectRequestDTO {
    private String subjectName;

    public SubjectRequestDTO(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public SubjectRequestDTO() {
    }
}
