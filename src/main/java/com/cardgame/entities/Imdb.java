package com.cardgame.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cardgame.enu.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Imdb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "film_name")
    private String filmName;

    @Column
    private BigDecimal note;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Imdb(String filmName, BigDecimal note, Gender gender) {
        this.filmName = filmName;
        this.note = note;
        this.gender = gender;
    }

}
