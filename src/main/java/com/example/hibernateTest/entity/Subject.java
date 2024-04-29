package com.example.hibernateTest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tantargy", schema = "")
@Getter
@Setter
public class Subject {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nev")
    private String title;

    @Column(name = "kredit")
    private int credit;

    @JoinColumn(name = "tanar_id")
    @OneToOne
    private Teacher teacher_id;
}
