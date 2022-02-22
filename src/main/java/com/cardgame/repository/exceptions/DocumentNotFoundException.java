package com.cardgame.repository.exceptions;

public class DocumentNotFoundException extends Exception{

    /**O
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String error;

    public DocumentNotFoundException(String message) {
        super(message);
        this.error = message;
    }
}
