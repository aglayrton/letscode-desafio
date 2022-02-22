package com.cardgame.repository.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cardgame.service.exceptions.DatabaseException;
import com.cardgame.service.exceptions.ResourceEntityNotFoundException;

//essa anotação permite que a classe intercepte alguma exceção
@ControllerAdvice
public class ResourceExcpetionHandler {
	
	//a anotação captura a determinada exceção.class
	//o método deve ser publico (a exceção, objeto especial do java web que contem as requisições)
	@ExceptionHandler(ResourceEntityNotFoundException.class)
	public ResponseEntity<StandarError> entityNotFound(ResourceEntityNotFoundException e, HttpServletRequest request){
		StandarError err = new StandarError();
		err.setTimestamp(Instant.now()); //pega o horario atual
		err.setStatus(HttpStatus.NOT_FOUND.value()); //pega o status do erro 404 .value converte em numero
		err.setError("Resource not found"); // mostra uma mensagem
		err.setMessage(e.getMessage()); //pega a mensagem la do service
		err.setPath(request.getRequestURI()); //pega o caminho
		//retorna o status junto as informações
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandarError> databaseNotFound(DatabaseException e, HttpServletRequest request){
		HttpStatus status =  HttpStatus.BAD_REQUEST;
		StandarError err = new StandarError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database Excpetion");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}
