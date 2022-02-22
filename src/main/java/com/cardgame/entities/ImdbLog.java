package com.cardgame.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.cardgame.enu.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "imdb_log")
@NoArgsConstructor
@AllArgsConstructor
public class ImdbLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "publicid")
    private UUID publicId;

    @Column(name = "last_item_order_scraped")
    private  int lastItemOrderScraped;

    @Column
    @Enumerated(EnumType.STRING)
    private  Gender gender;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "last_try")
    private Date lastTry;

    @Column
    private boolean sucess;

    public ImdbLog(int lastItemOrderScraped, Gender gender, boolean sucess) {
        this.lastItemOrderScraped = lastItemOrderScraped;
        this.gender = gender;
        this.sucess = sucess;
    }

    @PrePersist
    private void prePersist(){
        this.publicId = UUID.randomUUID();
    }
}
