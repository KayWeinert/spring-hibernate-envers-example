package de.kw.example.springhibernateenvers.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import de.kw.example.springhibernateenvers.entity.CourseStudent;
import de.kw.example.springhibernateenvers.entity.CourseStudentID;

@Repository
@Transactional
public class CourseStudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CourseStudent> findAllCourseStudents() throws Exception {
        CriteriaQuery<CourseStudent> query = entityManager.getCriteriaBuilder().createQuery(CourseStudent.class);
        query.select(query.from(CourseStudent.class));
        return entityManager.createQuery(query).getResultList();
    }

    public CourseStudent findCourseStudent(long id) throws Exception {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CourseStudent> query = cb.createQuery(CourseStudent.class);


        final Root<CourseStudent> root = query.from(CourseStudent.class);
        query.select(root).where(cb.equal(root.get("id"), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    public CourseStudent saveCourseStudent(CourseStudent courseStudent) {
        if (courseStudent.getCourseStudentID() == null) {
            CourseStudentID courseStudentID = new CourseStudentID(courseStudent.getStudent().getId(),
                    courseStudent.getCourse().getId());
            courseStudent.setCourseStudentID(courseStudentID);
            entityManager.persist(courseStudent);
        } else {
            entityManager.merge(courseStudent);
        }
        entityManager.flush();
        return courseStudent;
    }
}
