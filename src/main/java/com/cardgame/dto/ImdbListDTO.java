package com.cardgame.dto;

import java.io.Serializable;
import java.util.List;

public class ImdbListDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<ImdbRequiredDTO> imdb;

	public List<ImdbRequiredDTO> getImdb() {
		return imdb;
	}

	public void setImdb(List<ImdbRequiredDTO> imdb) {
		this.imdb = imdb;
	}

}
