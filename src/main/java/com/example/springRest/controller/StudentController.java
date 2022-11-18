package com.example.springRest.controller;

import com.example.springRest.dto.StudentDTO;
import com.example.springRest.entity.StudentEntity;
import com.example.springRest.exp.StudentCreationException;
import com.example.springRest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<?> createStudent(@RequestBody StudentDTO dto) {
        try {
            String result = studentService.createStudent(dto);
            return ResponseEntity.ok(result);
        } catch (StudentCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/studentALL")
    public ResponseEntity<?> createStudentAll(@RequestBody List<StudentDTO> dtoList) {
        try {
            String result = studentService.createStudentAll(dtoList);
            return ResponseEntity.ok(result);
        } catch (StudentCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getstudentList")
    public ResponseEntity<?> getStudentList() {
        try {
            List<StudentDTO> studentList = studentService.getStudentList();
            return ResponseEntity.ok(studentList);
        } catch (StudentCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/studentById/{id}")
    public ResponseEntity<?> geStudentId(@PathVariable Integer id) {
        try {
            StudentDTO studentDTO = studentService.getStudentId(id);
            return ResponseEntity.ok(studentDTO);
        } catch (StudentCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/studentDeleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            String result = studentService.deleteById(id);
            return ResponseEntity.ok(result);
        }catch (StudentCreationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/studentUpdatedID/{id}")
    public ResponseEntity<?> updateById(@RequestBody StudentDTO dto, @PathVariable Integer id) {
        try {
            String result = studentService.updateById(dto, id);
            return ResponseEntity.ok(result);
        } catch (StudentCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
