package com.example.springRest.service;

import com.example.springRest.dto.BookDTO;
import com.example.springRest.entity.BookEntity;
import com.example.springRest.exp.BookCreationException;
import com.example.springRest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDTO createBook(BookDTO dto) {

        BookDTO checked = check(dto);
        if (checked != null) {
            return checked;
        }
        BookEntity entity=new BookEntity();
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setPublishYear(dto.getPublishYear());
        bookRepository.createBook(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public String createAll(List<BookDTO>bookDTOS){
        if(bookDTOS.isEmpty()){
            throw new BookCreationException("bookDtos is required");
        }
        for (BookDTO bookDTO : bookDTOS) {
              createBook(bookDTO);
        }
        return "Succes added";
    }
    private  BookDTO check(BookDTO dto) {
        if (dto.getTitle().isBlank()||dto.getTitle().length()<=3) {
            throw new BookCreationException("title is required");
        }

        if (dto.getAuthor().isBlank() || dto.getAuthor().length()<=3) {
            throw new BookCreationException("author is required");
        }

        if (dto.getPublishYear()==null) {
            throw new BookCreationException("publisherYear is required");
        }
        return null;
    }

    public List<BookDTO> getBookList() {

        List<BookEntity>bookList=bookRepository.getBookList();
        if(bookList.isEmpty()){
            throw new BookCreationException("Book does not exists");
        }
        List<BookDTO>dtoList=new LinkedList<>();
        for (BookEntity entity : bookList) {
            BookDTO dto=new BookDTO();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setAuthor(entity.getAuthor());
            dto.setPublishYear(entity.getPublishYear());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public BookDTO getBookId(Integer id) {
        BookEntity entity=bookRepository.getBookById(id);
        if(entity==null){
            throw new BookCreationException("this id is not exists");
        }
        BookDTO dto=new BookDTO();
         dto.setId(entity.getId());
         dto.setTitle(entity.getTitle());
         dto.setAuthor(entity.getAuthor());
         dto.setPublishYear((entity.getPublishYear()));
         return dto;
    }

    public String deleteById(Integer id) {
        if (id < 0) {
            throw new BookCreationException("Id must be positive");
        }
        if (bookRepository.getBookById(id) == null) {
            throw new BookCreationException("this id book is not exists");
        }
        bookRepository.deleteById(id);
        return "succes deleted";

    }

    public String updateById(BookDTO bookDTO,Integer id) {

       BookEntity bookEntity = bookRepository.getBookById(id);
       if(bookEntity!=null) {
           bookEntity.setTitle(bookDTO.getTitle());
           bookEntity.setAuthor(bookDTO.getAuthor());
           bookEntity.setPublishYear(bookDTO.getPublishYear());
           bookRepository.updateById(bookEntity);
           return "Success Updated";
       }
        throw new BookCreationException("this id book is not exists");

    }
}
