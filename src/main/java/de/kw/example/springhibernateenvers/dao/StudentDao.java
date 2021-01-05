package de.kw.example.springhibernateenvers.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import de.kw.example.springhibernateenvers.entity.Student;

@Repository
@Transactional
public class StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Student> findAllStudents() throws Exception {
        CriteriaQuery<Student> query = entityManager.getCriteriaBuilder().createQuery(Student.class);
        query.select(query.from(Student.class));
        return entityManager.createQuery(query).getResultList();
    }

    public Student findStudent(long id) throws Exception {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);


        final Root<Student> root = query.from(Student.class);
        query.select(root).where(cb.equal(root.get("id"), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    public Student saveStudent(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
        entityManager.flush();
        return student;
    }

    public void deleteByLastname(String lastname){
        final Query query = entityManager.createNamedQuery(Student.QUERY_DELETE_STUDENT_BY_LASTNAME);
        query.setParameter("lastname", lastname);
        query.executeUpdate();
    }

    public void updateAllStudentsLastname(String lastname){
        final Query query = entityManager.createNamedQuery(Student.QUERY_UPDATE_STUDENT_LASTNAME);
        query.setParameter("lastname", lastname);
        query.executeUpdate();
    }
}
