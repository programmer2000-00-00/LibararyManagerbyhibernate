package com.example.springRest.repository;

import com.example.springRest.entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void createBook(BookEntity book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer save = (Integer) session.save(book);
        transaction.commit();
        session.close();
    }

    public List<BookEntity> getBookList() {
        Session session = sessionFactory.openSession();
        List<BookEntity>bookList = session.createQuery("From BookEntity order by id asc").getResultList();
        session.close();
        return bookList;
    }

    public void deleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getBookById((id)));
        transaction.commit();
        session.close();

    }

    public void updateById(BookEntity book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(book);
        transaction.commit();
        session.close();
    }

    public BookEntity getBookById(int bookId) {
        Session session = sessionFactory.openSession();
        BookEntity book = session.get(BookEntity.class, bookId);
        session.close();
        return book;
    }
}

