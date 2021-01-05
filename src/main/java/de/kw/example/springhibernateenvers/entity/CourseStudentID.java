package de.kw.example.springhibernateenvers.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseStudentID implements Serializable {

    private static final long serialVersionUID = 251636139412771424L;

    @Column(name = "student_id", nullable = false)
    private long studentId;

    @Column(name = "course_id", nullable = false)
    private long courseId;

    public CourseStudentID(long studentId, long courseId) {

        this.studentId = studentId;
        this.courseId = courseId;
    }

    public CourseStudentID() {
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudentID that = (CourseStudentID) o;
        return studentId == that.studentId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}