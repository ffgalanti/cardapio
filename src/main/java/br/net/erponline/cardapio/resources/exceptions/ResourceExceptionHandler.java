package br.net.erponline.cardapio.resources.exceptions;

import java.time.Instant;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.net.erponline.cardapio.services.exceptions.DatabaseException;
import br.net.erponline.cardapio.services.exceptions.ResourceNotFoundException;
import br.net.erponline.cardapio.utils.Translators;

// captura as exceções para poder fazer tratamento
@ControllerAdvice 
public class ResourceExceptionHandler {

	static ResourceBundle resourceBundle = new Translators().getIdioma();
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = resourceBundle.getString("error.NotFound");
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = resourceBundle.getString("error.DataBase");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
}