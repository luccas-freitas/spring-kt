package com.deunacabeca.api.controller.exception;

public class PessoaNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PessoaNotFoundException(Long id) {
		super("Pessoa " + id + " não encontrado.");
	}
}
