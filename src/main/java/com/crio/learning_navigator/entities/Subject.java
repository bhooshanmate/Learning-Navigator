package com.crio.learning_navigator.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject_details")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;

    @JsonBackReference
    @ManyToMany(mappedBy = "enrolledSubjects")
    private List<Student> registeredStudents;

    public Subject() {}

    public Long getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setRegisteredStudents(List<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", registeredStudents=" + registeredStudents +
                '}';
    }
}
