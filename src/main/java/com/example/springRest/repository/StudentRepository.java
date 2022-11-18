package com.example.springRest.repository;

import com.example.springRest.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
@Repository
public class StudentRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public Integer createStudent(StudentEntity student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer save = (Integer) session.save(student);
        transaction.commit();
        session.close();
        return save;
    }

    public List<StudentEntity> getStudentList() {
        Session session = sessionFactory.openSession();
        List<StudentEntity> studentList = session.createQuery("From StudentEntity order by id asc").getResultList();
        session.close();
        return studentList;
    }

    public StudentEntity getStudentId(Integer id) {
        Session session = sessionFactory.openSession();
       StudentEntity student = session.get(StudentEntity.class, id);
       return student;
    }

    public void deleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.delete(getStudentId(id));
        transaction.commit();
        session.close();
    }

    public void updateById(StudentEntity student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
    }

    public StudentEntity getStudentById(int studentId) {
        Session session = sessionFactory.openSession();

        StudentEntity student = session.get(StudentEntity.class, studentId);
        return student;
    }

}
