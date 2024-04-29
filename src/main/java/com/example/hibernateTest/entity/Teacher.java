package com.example.hibernateTest.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tanar", schema = "")
@Getter
@Setter
public class Teacher {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "vezeteknev")
    private String lastname;

    @Column(name = "keresztnev")
    private String firstname;

    @Column(name = "szuletesi_datum")
    private LocalDate dateOfBirth;

    @Column(name = "szuletesi_hely")
    private String placeOfBirth;
}
