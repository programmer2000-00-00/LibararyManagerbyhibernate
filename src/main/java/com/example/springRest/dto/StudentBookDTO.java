package com.example.springRest.dto;

import com.example.springRest.enums.StatusBy;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentBookDTO {
    private Integer id;
    private Integer studentId;
    private Integer bookId;
    private Integer durationDay;

    private StudentDTO student;
    private BookDTO book;

    private LocalDateTime createdDate;
    private StatusBy status;
    private LocalDateTime returnedDate;
}
