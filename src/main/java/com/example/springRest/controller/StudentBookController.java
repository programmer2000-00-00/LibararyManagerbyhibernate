package com.example.springRest.controller;

import com.example.springRest.dto.StudentBookDTO;
import com.example.springRest.dto.StudentDTO;
import com.example.springRest.exp.StudentBookCreationExp;
import com.example.springRest.service.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentBookController {
    @Autowired

    private StudentBookService service;

    @PostMapping("/student_book")
    public ResponseEntity<?> studentTakeBook(@RequestBody StudentBookDTO dto) {
        try {
            StudentBookDTO studentBookDTO = service.studentTakeBook(dto);
            return ResponseEntity.ok(studentBookDTO);
        } catch (StudentBookCreationExp e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/student_book/{bookId}")
    public ResponseEntity<?> returnBook(@PathVariable int bookId) {
        try {
            String returnedBook = service.returnBook(bookId);
            return ResponseEntity.ok(returnedBook);
        } catch (StudentBookCreationExp e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
       @GetMapping("/getStudent_Book_list")
               public ResponseEntity<?>getStudentBookList(){
           try{
               List<StudentBookDTO>studentBookDTOList=service.studentBookDTOS();
               return ResponseEntity.ok(studentBookDTOList);

             }catch (StudentBookCreationExp e) {
               return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/getStudent_Book_id/{id}")
    public ResponseEntity<?>getStudentBookById(@PathVariable("id") Integer id){
        try{
            StudentBookDTO dto=service.getSBbyID(id);
            return ResponseEntity.ok(dto);

        }catch (StudentBookCreationExp e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/getStudent_Book_idlist/{id}")
    public ResponseEntity<?>getStudentBookTakenId(@PathVariable("id") Integer id){
        try{
            List<StudentBookDTO> studentBookDTOList=service.getStudentBookTakenId(id);
            return ResponseEntity.ok(studentBookDTOList);

        }catch (StudentBookCreationExp e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/getStudent_Book_idBooklist/{id}")
    public ResponseEntity<?> getStudentBookTakenBookId(@PathVariable("id") Integer id){
        try{
            List<StudentBookDTO> studentBookDTOList=service. getStudentBookTakenBookId(id);
            return ResponseEntity.ok(studentBookDTOList);

        }catch (StudentBookCreationExp e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
