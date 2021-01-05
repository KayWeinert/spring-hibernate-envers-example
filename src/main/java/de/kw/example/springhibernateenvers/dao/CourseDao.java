package de.kw.example.springhibernateenvers.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import de.kw.example.springhibernateenvers.entity.Course;
import de.kw.example.springhibernateenvers.entity.Student;

@Repository
@Transactional
public class CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Course> findAllCourses() throws Exception {
        CriteriaQuery<Course> query = entityManager.getCriteriaBuilder().createQuery(Course.class);
        query.select(query.from(Course.class));
        return entityManager.createQuery(query).getResultList();
    }

    public Course findCourse(long id) throws Exception {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);


        final Root<Course> root = query.from(Course.class);
        query.select(root).where(cb.equal(root.get("id"), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    public Course saveCourse(Course Course) {
        if (Course.getId() == null) {
            entityManager.persist(Course);
        } else {
            entityManager.merge(Course);
        }
        entityManager.flush();
        return Course;
    }
}
