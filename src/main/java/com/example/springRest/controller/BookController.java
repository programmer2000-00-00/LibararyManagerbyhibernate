package com.example.springRest.controller;

import com.example.springRest.dto.BookDTO;
import com.example.springRest.entity.BookEntity;
import com.example.springRest.exp.BookCreationException;
import com.example.springRest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        try{
        BookDTO dto = bookService.createBook(bookDTO);
        return ResponseEntity.ok(dto);
        }catch (BookCreationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping("/bookCreateALL")
    public ResponseEntity<?> createAll(@RequestBody List<BookDTO>bookDTOList) {
        try{
            String str = bookService.createAll(bookDTOList);
            return ResponseEntity.ok(str);
        }catch (BookCreationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/book_all")
    public ResponseEntity<?> getBookList() {
        try {
            List<BookDTO> bookList = bookService.getBookList();
            return ResponseEntity.ok(bookList);
        } catch (BookCreationException e){
            return (ResponseEntity.badRequest().body(e.getMessage()));
        }

    }

    @GetMapping("/book_byId/{id}")
    public ResponseEntity<?> getBookId(@PathVariable("id") Integer id) {
        try {
            BookDTO dto = bookService.getBookId(id);
            return ResponseEntity.ok(dto);
        }catch (BookCreationException e){
            return (ResponseEntity.badRequest().body(e.getMessage()));
        }
    }

    @DeleteMapping("/book_delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            String result = bookService.deleteById(id);
            return ResponseEntity.ok(result);
        }catch (BookCreationException e){
         return (ResponseEntity.badRequest().body(e.getMessage()));
        }

    }

    @PutMapping("/bookUpdated/{id}")
    public ResponseEntity<?> updateById(@RequestBody BookDTO dto, @PathVariable Integer id){
        try {
            String result = bookService.updateById(dto, id);
            return ResponseEntity.ok(result);
        }catch (BookCreationException e){
            return (ResponseEntity.badRequest().body(e.getMessage()));
        }

    }

}
