package com.example.exam.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "s_cards")
@Getter
public class SCard implements Serializable {

    @Id
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "date_out")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOut;

    @Setter
    @Column(name = "date_in")
    private Date dateIn;

    @Setter
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Setter
    @ManyToOne
    @JoinColumn(name = "lib_id", nullable = false)
    private Librarian librarian;

    @Setter
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}


