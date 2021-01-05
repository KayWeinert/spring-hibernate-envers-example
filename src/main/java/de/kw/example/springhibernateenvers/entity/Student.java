package de.kw.example.springhibernateenvers.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * 1:n - Student : Thesis
 * 1:n - Student : CourseStudent (n:m - Student : CourseStudent : Course)
 * n:m - Student : Mentor
 */
@Entity
@Table(name = "student")
@Audited
@NamedQuery(name = Student.QUERY_DELETE_STUDENT_BY_LASTNAME, query = "DELETE FROM Student where lastName = :lastname")
@NamedQuery(name = Student.QUERY_UPDATE_STUDENT_LASTNAME, query = "Update Student set lastName = :lastname")
public class Student {

    public static final String QUERY_DELETE_STUDENT_BY_LASTNAME = "deleteStudentByLastname";
    public static final String QUERY_UPDATE_STUDENT_LASTNAME = "updateStudentLastname";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Thesis> theses = null;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = CourseStudent.class, mappedBy = "student")
    private Set<CourseStudent> courses = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "mentor_student",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private Set<Mentor> mentors = new HashSet<>();

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Thesis> getTheses() {
        return theses;
    }

    public void setTheses(Collection<Thesis> theses) {
        this.theses = new HashSet<>(theses);
    }

    public Set<CourseStudent> getCourses() {
        return courses;
    }

    public void setCourses(Collection<CourseStudent> courses) {
        this.courses = new HashSet<>(courses);
    }

    public Set<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(Collection<Mentor> mentors) {
        this.mentors = new HashSet<>(mentors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        return lastName != null ? lastName.equals(student.lastName) : student.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

}
