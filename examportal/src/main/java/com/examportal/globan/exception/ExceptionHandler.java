package com.examportal.globan.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.examportal.bean.ValidationResponse;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {

		/*
		 * Map<String, String> collect =
		 * exception.getBindingResult().getAllErrors().stream()
		 * .collect(Collectors.toMap(ob -> ((FieldError) ob).getField(), ob ->
		 * ob.getDefaultMessage()));
		 */

		List<String> exceptionalErrors = exception.getBindingResult().getFieldErrors().stream()
				.map(x -> x.getDefaultMessage()).collect(Collectors.toList());

		return new ResponseEntity<>(new ValidationResponse(new Date(), httpStatus.value(), exceptionalErrors),
				httpStatus);
	}
}
