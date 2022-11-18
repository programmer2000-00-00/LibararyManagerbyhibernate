package com.example.springRest.service;

import com.example.springRest.dto.StudentBookDTO;
import com.example.springRest.dto.StudentDTO;
import com.example.springRest.entity.BookEntity;
import com.example.springRest.entity.StudentEntity;
import com.example.springRest.entity.StudentBookEntity;
import com.example.springRest.enums.StatusBy;
import com.example.springRest.exp.StudentBookCreationExp;
import com.example.springRest.repository.BookRepository;
import com.example.springRest.repository.StudentBookRepository;
import com.example.springRest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentBookService {
    @Autowired
    private StudentBookRepository repository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BookRepository bookRepository;

    public StudentBookDTO studentTakeBook(StudentBookDTO dto) {

        BookEntity book = bookRepository.getBookById(dto.getBookId());
        if (book == null) {
            throw new StudentBookCreationExp("This id book not found");
        }

        StudentEntity student = studentRepository.getStudentById(dto.getStudentId());
        if (student == null) {
            throw new StudentBookCreationExp("This id Student not found");
        }

        StudentBookEntity studentBook = new StudentBookEntity();
        studentBook.setStudent(student);
        studentBook.setBook(book);
        studentBook.setDuration(dto.getDurationDay());
        studentBook.setCreatedDate(LocalDate.now());
        studentBook.setStatus(StatusBy.ONHAND);
        repository.takingBook(studentBook);
        dto.setId(studentBook.getId());
        return dto;

    }

    public String returnBook(int bookId) {
        StudentBookEntity book = repository.getStudentBookId(bookId);
        if (book == null) {
            throw new StudentBookCreationExp("this id student did not take book");
        }
        book.setStatus(StatusBy.RETURNED);
        book.setReturnedDate(LocalDate.now());
        repository.returnBook(book);
        return "Updated";
    }

    public List<StudentBookDTO> studentBookDTOS() {
        List<StudentBookEntity> studentBookEntityList = repository.getStudentBookList();
        if (studentBookEntityList.isEmpty()) {
            throw new StudentBookCreationExp("StudentBookList is empty");
        }
        List<StudentBookDTO> studentBookDTOS = new LinkedList<>();
        for (StudentBookEntity studentBookEntity : studentBookEntityList) {
            StudentBookDTO studentBookDTO = new StudentBookDTO();
            studentBookDTO.setId(studentBookEntity.getId());
            studentBookDTO.setBookId(studentBookEntity.getBook().getId());
            studentBookDTO.setStudentId(studentBookEntity.getStudent().getId());
            studentBookDTO.setDurationDay(studentBookEntity.getDuration());
            studentBookDTO.setStatus(studentBookEntity.getStatus());
            studentBookDTO.setReturnedDate(studentBookEntity.getReturnedDate().atStartOfDay());
            studentBookDTOS.add(studentBookDTO);
        }
        return studentBookDTOS;

    }
    public StudentBookDTO getSBbyID(int bookId) {
        StudentBookEntity studentBookEntity = repository.getStudentBookId(bookId);
        if (studentBookEntity== null) {
            throw new StudentBookCreationExp("this id student did not take book");
        }
        StudentBookDTO studentBookDTO=new StudentBookDTO();
        studentBookDTO.setId(bookId);
        studentBookDTO.setStudentId(studentBookEntity.getStudent().getId());
        studentBookDTO.setBookId(studentBookEntity.getBook().getId());
        studentBookDTO.setDurationDay(studentBookEntity.getDuration());
        studentBookDTO.setReturnedDate(studentBookEntity.getReturnedDate().atStartOfDay());
        studentBookDTO.setStatus(studentBookEntity.getStatus());
        return studentBookDTO;

    }
    public List<StudentBookDTO> getStudentBookTakenId(Integer id){
        List<StudentBookEntity>studentBookEntityList=repository.getStudentBookTakenId(id);
        List<StudentBookDTO>studentBookDTOList=new LinkedList<>();
        for (StudentBookEntity bookEntity : studentBookEntityList) {
            StudentBookDTO studentBookDTO=new StudentBookDTO();
            studentBookDTO.setBookId(bookEntity.getBook().getId());
            studentBookDTO.setDurationDay(bookEntity.getDuration());
            studentBookDTO.setStatus(bookEntity.getStatus());
            studentBookDTOList.add(studentBookDTO);
        }
        return studentBookDTOList;


    }
    public List<StudentBookDTO>  getStudentBookTakenBookId(Integer id){
        List<StudentBookEntity>studentBookEntityList=repository. getStudentBookTakenBookId(id);
        List<StudentBookDTO>studentBookDTOList=new LinkedList<>();
        for (StudentBookEntity bookEntity : studentBookEntityList) {
            StudentBookDTO studentBookDTO=new StudentBookDTO();
            studentBookDTO.setStudentId(bookEntity.getStudent().getId());
            studentBookDTO.setDurationDay(bookEntity.getDuration());
            studentBookDTO.setStatus(bookEntity.getStatus());
            studentBookDTOList.add(studentBookDTO);
        }
        return studentBookDTOList;


    }
}

