package com.cardgame.dto;

import java.math.BigDecimal;

public class ImdbRequiredDTO extends ImdbDTO{
	private static final long serialVersionUID = 1L;
	
	private BigDecimal note;
	private boolean selected;
	
	public ImdbRequiredDTO(Long id, String filmName, BigDecimal note,  boolean selected) {
		super(id, filmName, note);
		this.note = note;
		this.selected = selected;
	}
	
	public ImdbRequiredDTO() {
		super();
	}

	public BigDecimal getNote() {
		return note;
	}

	public void setNote(BigDecimal note) {
		this.note = note;
	}

	public boolean isselected() {
		return selected;
	}

	public void setselected(boolean selected) {
		this.selected = selected;
	}
	
	
	
}
