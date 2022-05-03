package org.itech.equipment.controllerAdvice;

import java.util.Date;

import org.itech.equipment.config.ErrorMessage;
import org.itech.equipment.exception.OperationFailedException;
import org.itech.equipment.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(OperationFailedException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage operationFailedException(OperationFailedException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
	}

}
