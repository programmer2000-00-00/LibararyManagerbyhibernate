package com.example.springRest.repository;

import com.example.springRest.entity.BookEntity;
import com.example.springRest.entity.StudentBookEntity;
import com.example.springRest.entity.StudentEntity;
import com.example.springRest.enums.StatusBy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class StudentBookRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public void takingBook(StudentBookEntity studentBook) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(studentBook);

        transaction.commit();
        session.close();

        }


    public void returnBook(StudentBookEntity book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(book);
        transaction.commit();
        session.close();
    }

    public StudentBookEntity getStudentBookId(Integer id){
        Session session = sessionFactory.openSession();
        StudentBookEntity studentEntity=session.get(StudentBookEntity.class,id);
        session.close();
        return studentEntity;
    }
    public List<StudentBookEntity> getStudentBookList() {
        Session session = sessionFactory.openSession();
        List<StudentBookEntity>studentBookList = session.createQuery("From StudentBookEntity order by id asc").getResultList();
        session.close();
        return studentBookList;
    }
    public List<StudentBookEntity> getStudentBookTakenId(Integer id){
        Session session = sessionFactory.openSession();
        List<StudentBookEntity>studentBookList = session.createQuery("From StudentBookEntity where student.id= "+id+" order by id asc").getResultList();
        session.close();
        return studentBookList;
    }
    public List<StudentBookEntity> getStudentBookTakenBookId(Integer id){
        Session session = sessionFactory.openSession();
        List<StudentBookEntity>studentBookList = session.createQuery("From StudentBookEntity where book.id= "+id+" order by id asc").getResultList();
        session.close();
        return studentBookList;
    }
}

