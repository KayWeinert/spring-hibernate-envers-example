package de.kw.example.springhibernateenvers.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kw.example.springhibernateenvers.dao.CourseDao;
import de.kw.example.springhibernateenvers.dao.CourseStudentDao;
import de.kw.example.springhibernateenvers.dao.StudentDao;
import de.kw.example.springhibernateenvers.entity.Course;
import de.kw.example.springhibernateenvers.entity.CourseStudent;
import de.kw.example.springhibernateenvers.entity.Student;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseStudentDao courseStudentDao;


    @Transactional
    public List<Student> fetchAllStudents() throws Exception {

        List<Student> allStudents = studentDao.findAllStudents();

        if (allStudents.isEmpty()) {
            insertDefaults();
            allStudents = studentDao.findAllStudents();
        }

        return allStudents;
    }

    @Transactional
    public Student saveStudent(Student student) {
        return studentDao.saveStudent(student);
    }


    @Transactional
    public Student findStudent(long id) throws Exception {
        return studentDao.findStudent(id);
    }

    @Transactional
    void insertDefaults() {
        final Student student = saveStudent(new Student("Luke", "Skywalker"));

        Course course = new Course("Math");
        course = courseDao.saveCourse(course);

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudentDao.saveCourseStudent(courseStudent);

        student.getCourses().add(courseStudent);

        course = new Course("English");
        course = courseDao.saveCourse(course);

        courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudentDao.saveCourseStudent(courseStudent);

        student.getCourses().add(courseStudent);

        saveStudent(student);
    }

    @Transactional
    public void deleteByLastname(String lastname){
        studentDao.deleteByLastname(lastname);
    }

    @Transactional
    public void updateAllStudentsLastname(String lastname){
        studentDao.updateAllStudentsLastname(lastname);
    }
}
