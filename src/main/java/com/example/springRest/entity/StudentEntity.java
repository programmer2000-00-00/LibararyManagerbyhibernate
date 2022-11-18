package com.example.springRest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 25, nullable = false)
    private String name;
    @Column(length = 25, nullable = false)
    private String surname;
    @Column(length = 13, nullable = false)
    private String phone;
    @Column
    private LocalDate createDate;

}
