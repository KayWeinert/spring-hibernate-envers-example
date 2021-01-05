package de.kw.example.springhibernateenvers.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.kw.example.springhibernateenvers.dto.StudentResponse;
import de.kw.example.springhibernateenvers.entity.Student;
import de.kw.example.springhibernateenvers.service.StudentService;


@RestController
@RequestMapping("/api")
@Transactional
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students")
    public ResponseEntity<StudentResponse> getStudents() throws Exception {
        return new ResponseEntity<>(getAllStudentResponse(), HttpStatus.OK);
    }

    @GetMapping(value = "/students/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Student> getStudent(@PathVariable long id) throws Exception {
        return new ResponseEntity<>(studentService.findStudent(id), HttpStatus.OK);
    }

    @PatchMapping(value = "/students")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<Student> patchStudent(@RequestBody Student student) {
        final Student saved = studentService.saveStudent(student);
        return new ResponseEntity<>(saved, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        final Student saved = studentService.saveStudent(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/students/{lastname}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<StudentResponse> deleteStudentsByLastname(@PathVariable String lastname) throws Exception {
        studentService.deleteByLastname(lastname);
        return new ResponseEntity<>(getAllStudentResponse(), HttpStatus.OK);
    }

    @PatchMapping(value = "/students/{lastname}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<StudentResponse> updateAllStudentsLastname(@PathVariable String lastname) throws Exception {
        studentService.updateAllStudentsLastname(lastname);
        return new ResponseEntity<>(getAllStudentResponse(), HttpStatus.OK);
    }

    StudentResponse getAllStudentResponse() throws Exception {
        StudentResponse response = new StudentResponse();
        response.setStudents(studentService.fetchAllStudents());
        return response;
    }

}
