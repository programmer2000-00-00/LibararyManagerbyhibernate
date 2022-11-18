package com.example.springRest.entity;

import com.example.springRest.enums.StatusBy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name="student_book")
public class StudentBookEntity {
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;
    @Column
    private LocalDate createdDate;
    @Column
    @Enumerated(EnumType.STRING)
    private StatusBy status;
    @Column
    private LocalDate returnedDate;
    @Column
    private Integer duration;


}
