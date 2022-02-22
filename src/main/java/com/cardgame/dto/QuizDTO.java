package com.cardgame.dto;

import java.io.Serializable;

import com.cardgame.entities.Quiz;
import com.cardgame.entities.User;

public class QuizDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private int pont;
	
	private User user;
	
	private int error;

	public QuizDTO(Long id, int pont) {
		this.id = id;
		this.pont = pont;
	}
	
	public QuizDTO(Quiz quizEntity) {
		this.pont = quizEntity.getPont();
		this.user = quizEntity.getUser();
	}
	
	public QuizDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setPont(int pont) {
		this.pont = pont;
	}

	public int getPont() {
		return pont;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
	
	
}
