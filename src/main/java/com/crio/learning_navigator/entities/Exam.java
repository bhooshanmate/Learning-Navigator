package com.crio.learning_navigator.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exam_details")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @JsonBackReference
    @ManyToMany(mappedBy = "registeredExams")
    private List<Student> enrolledStudents;

    public Exam(){}

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", subject=" + subject +
                ", enrolledStudents=" + enrolledStudents +
                '}';
    }
}
