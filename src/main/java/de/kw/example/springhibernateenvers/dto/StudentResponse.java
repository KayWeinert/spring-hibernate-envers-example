package de.kw.example.springhibernateenvers.dto;

import java.util.List;

import de.kw.example.springhibernateenvers.entity.Student;

public class StudentResponse {
    List<Student> students;

    public StudentResponse() {
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
