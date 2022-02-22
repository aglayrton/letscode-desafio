package com.cardgame.dto;

import java.io.Serializable;

public class DTOFilmes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Title;
	private Double imdbRating;
	
	public DTOFilmes(String Title, String imdbRating) {
		this.Title = Title;
		this.imdbRating = Double.parseDouble(imdbRating);
	}
	
	public DTOFilmes(DTOFilmes x) {
		this.Title = x.getTitle();
		this.imdbRating = x.getImdbRating();
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public Double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = Double.parseDouble(imdbRating);
	}
	
	
	
}
