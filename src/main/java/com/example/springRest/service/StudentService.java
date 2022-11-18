package com.example.springRest.service;

import com.example.springRest.dto.StudentDTO;
import com.example.springRest.entity.StudentEntity;
import com.example.springRest.exp.StudentCreationException;
import com.example.springRest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String createStudent(StudentDTO studentDTO) {
        String checked = check(studentDTO);
        if (checked != null) {
            return checked;
        }
        StudentEntity entity=new StudentEntity();
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        entity.setPhone(studentDTO.getPhone());
        entity.setCreateDate(studentDTO.getCreatedDate());
        Integer student1 = studentRepository.createStudent(entity);
        if (student1 != 0) {
            return "Successefully added";
        }
        return "not added";

    }
    private String check(StudentDTO studentDTO) {
        if (studentDTO.getName().isEmpty() || studentDTO.getName().isBlank()) {
            throw new StudentCreationException("title is required");
        }

        if (studentDTO.getSurname().isEmpty() || studentDTO.getSurname().isBlank()) {
            throw new StudentCreationException("Author is required");
        }

        if (!studentDTO.getPhone().startsWith("+998")) {
            studentDTO.setPhone("+998" + studentDTO.getPhone());
        }

        if (studentDTO.getPhone().length() != 13) {
            throw new StudentCreationException("Phone number is wrong");
        }
        return null;
    }

    public List<StudentDTO> getStudentList() {
        List<StudentEntity> entityList=studentRepository.getStudentList();
        if(entityList.isEmpty()){
            throw new StudentCreationException("Student is not exists");
        }
        List<StudentDTO>dtoList=new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto=new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setPhone(entity.getPhone());
            dto.setCreatedDate(entity.getCreateDate());
            dtoList.add(dto);
        }
        return dtoList;

    }

    public StudentDTO getStudentId(Integer id) {
        StudentEntity entity=studentRepository.getStudentId(id);
        if(entity==null){
            throw new StudentCreationException("this id student is not exists");
        }
        StudentDTO dto=new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhone(entity.getPhone());
        dto.setCreatedDate(entity.getCreateDate());
        return dto;

    }

    public String deleteById(Integer id) {
        if (id < 0) {
           throw new StudentCreationException("Id must be positive");
        }
        if (getStudentId(id) == null) {
            throw new StudentCreationException("this id student is not exists");
        }
        studentRepository.deleteById(id);
        return "succes deleted";
    }

    public String updateById(StudentDTO studentDTO,Integer id) {
       if(id<0){
           throw new StudentCreationException("id must be positive");
       }
      StudentEntity entity= studentRepository.getStudentId(id);
       if(entity!=null) {
           entity.setId(id);
           entity.setName(studentDTO.getName());
           entity.setSurname(studentDTO.getSurname());
           entity.setPhone(studentDTO.getPhone());
           entity.setCreateDate(studentDTO.getCreatedDate());
           studentRepository.updateById(entity);
           return "succes updated";
       }
       throw new StudentCreationException("this id student is not found");
    }
    public String createStudentAll(List<StudentDTO> dtoList) {
        if(dtoList.isEmpty()){ throw new StudentCreationException("dto list is required");}
        for (StudentDTO dto : dtoList) {
            createStudent(dto);
        }
        return "successefully added";
    }
}
