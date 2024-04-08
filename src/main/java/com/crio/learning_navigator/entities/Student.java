package com.crio.learning_navigator.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_details")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "student_exam",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id"))
    private List<Exam> registeredExams = new ArrayList<>();

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> enrolledSubjects = new ArrayList<>();

    public List<Exam> getRegisteredExams() {
        return registeredExams;
    }

    public void setRegisteredExams(List<Exam> registeredExams) {
        this.registeredExams = registeredExams;
    }

    public Student(){}

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setEnrolledSubjects(List<Subject> enrolledSubjects) {
        this.enrolledSubjects = enrolledSubjects;
    }

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Subject> getEnrolledSubjects() {
        return enrolledSubjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", registeredExams=" + registeredExams +
                ", enrolledSubjects=" + enrolledSubjects +
                '}';
    }
}
