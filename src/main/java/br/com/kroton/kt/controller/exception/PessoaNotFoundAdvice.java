package com.deunacabeca.api.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PessoaNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(PessoaNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	Map<String, String> pessoaNotFoundHandler (PessoaNotFoundException ex) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", ex.getMessage());
		return response;
	}
}
