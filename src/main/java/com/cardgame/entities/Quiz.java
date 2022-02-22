package com.cardgame.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_note")
public class Quiz implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int pont;
	
	private int error;
	
	private boolean gameOver;
	
	@ManyToOne
	@JoinColumn(name = "id_user_fk")
	private User user;

	public Quiz(int pont, User user, int error, boolean gameOver) {
		this.pont = pont;
		this.user = user;
		this.error = error;
		this.gameOver = gameOver;
	}

	public Quiz() {
	}
}
