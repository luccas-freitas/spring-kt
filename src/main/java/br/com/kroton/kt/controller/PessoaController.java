package br.com.kroton.kt.controller;

import java.util.List;

import com.deunacabeca.api.controller.exception.PessoaNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kroton.kt.model.Pessoa;
import br.com.kroton.kt.repository.PessoaRepository;
import lombok.AllArgsConstructor;

/**
 * PessoaController
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PessoaController {
    @Autowired
    private final PessoaRepository repository;

    //CRUD - CREATE
    @PostMapping(value = "/pessoas")
    public Pessoa newPessoa(@RequestBody Pessoa pessoa) {
        return repository.save(new Pessoa(
                pessoa.getId(), 
                pessoa.getNome(),
                pessoa.getDataNascimento(), 
                pessoa.getFilhos(), 
                pessoa.getSexo())
            );
    }

    //CRUD - READ
    @GetMapping(value = "/pessoas/{id}")
    Pessoa one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(id));
    }

    //CRUD - READ (ALL)
    @GetMapping(value = "/pessoas")
    public List<Pessoa> all() {
        return repository.findAll();
    }

    //CADASTRO - UPDATE
    @PutMapping(value = "/pessoas/{id}")
    public Pessoa replacePessoa(@PathVariable Long id, @RequestBody Pessoa newPessoa) {
        return repository.findById(id).map(
            pessoa -> {
                pessoa.setNome(newPessoa.getNome());
                pessoa.setDataNascimento(newPessoa.getDataNascimento());
                pessoa.setFilhos(newPessoa.getFilhos());
                pessoa.setSexo(newPessoa.getSexo());

                return repository.save(pessoa);
            }
        )
        .orElseGet(() ->{
            newPessoa.setId(id);
            return repository.save(newPessoa);
        });
    }

    //CADASTRO - DELETE
    @DeleteMapping(value = "/pessoas/{id}")
    void deletePessoa(@PathVariable Long id) {
        repository.deleteById(id);
    }
}