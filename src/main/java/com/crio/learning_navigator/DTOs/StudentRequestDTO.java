package com.crio.learning_navigator.DTOs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class StudentRequestDTO {
    private String name;

    public StudentRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
