package com.example.springRest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 25,nullable = false)
    private String title;
    @Column(length = 25,nullable = false)
    private String author;
    @Column
    private LocalDate publishYear;


}
