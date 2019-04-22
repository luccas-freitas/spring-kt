package br.com.kroton.kt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kroton.kt.model.Pessoa;

/**
 * PessoaRepository
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{}