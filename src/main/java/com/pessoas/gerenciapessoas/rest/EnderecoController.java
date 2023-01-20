package com.pessoas.gerenciapessoas.rest;

import com.pessoas.gerenciapessoas.entity.Endereco;
import com.pessoas.gerenciapessoas.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas/{pessoaId}/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listarEnderecosDaPessoa(@PathVariable Long pessoaId) {
        return enderecoService.listarEnderecosDaPessoa(pessoaId);
    }

    @PostMapping
    public Endereco salvarEndereco(@PathVariable Long pessoaId, @Validated @RequestBody Endereco endereco) {
        return enderecoService.salvarEndereco(pessoaId, endereco);
    }

    @GetMapping("/{id}")
    public Endereco buscarEnderecoPorId(@PathVariable Long id) {
        return enderecoService.buscarEnderecoPorId(id);
    }

    @PutMapping("/{id}")
    public Endereco atualizarEndereco(@PathVariable Long id, @Validated @RequestBody Endereco enderecoAt) {
        return enderecoService.atualizarEndereco(id, enderecoAt);
    }

    @DeleteMapping("/{id}")
    public void deletarEndereco(@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
    }

    @PutMapping("/{id}/principal")
    public Endereco atualizarEnderecoPrincipal(@PathVariable Long pessoaId, @PathVariable Long id) {
        return enderecoService.atualizarEnderecoPrincipal(pessoaId, id);
    }
}