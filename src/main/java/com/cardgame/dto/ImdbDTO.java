package com.cardgame.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cardgame.entities.Imdb;

public class ImdbDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String filmName;
	

	public ImdbDTO(Long id, String filmName, BigDecimal note) {
		this.id = id;
		this.filmName = filmName;
	}

	public ImdbDTO(Imdb entity) {
		id = entity.getId();
		filmName = entity.getFilmName();
	}

	public ImdbDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	
	
}
