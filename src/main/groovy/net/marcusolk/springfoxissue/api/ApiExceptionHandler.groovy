package net.marcusolk.springfoxissue.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import static org.springframework.http.ResponseEntity.status

@ControllerAdvice
class ApiExceptionHandler {

	@ExceptionHandler(Exception)
	ResponseEntity<String> handleInternalServerError(Exception cause) {
		status(INTERNAL_SERVER_ERROR).body("An unexpected error occured: $cause")
	}

}
