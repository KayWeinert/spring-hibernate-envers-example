package de.kw.example.springhibernateenvers.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * n:1 - CourseStudent : Student
 * n:1 - CourseStudent : Course
 */
@Entity
@Audited
public class CourseStudent {

    @EmbeddedId
    private CourseStudentID courseStudentID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    @NotAudited
    private Course course;

    @Column(name = "rating")
    private String rating;

    public CourseStudent() {
    }

    public CourseStudent(CourseStudentID courseStudentID) {
        this.courseStudentID = courseStudentID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public CourseStudentID getCourseStudentID() {
        return courseStudentID;
    }

    public void setCourseStudentID(CourseStudentID courseStudentID) {
        this.courseStudentID = courseStudentID;
    }
}
