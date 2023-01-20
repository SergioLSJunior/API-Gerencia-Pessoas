package com.pessoas.gerenciapessoas.service;

import com.pessoas.gerenciapessoas.entity.Pessoa;
import com.pessoas.gerenciapessoas.repository.PessoaRepository;
import com.pessoas.gerenciapessoas.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarTodasPessoas(){
        return pessoaRepository.findAll();
    }

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pessoa", "id", id));
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", id));
        pessoa.setNome(pessoaAtualizada.getNome());
        pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoa.setEnderecos(pessoaAtualizada.getEnderecos());
        Pessoa pessoaAtualizadaSalva = pessoaRepository.save(pessoa);
        return pessoaAtualizadaSalva;
    }

    public void deletarPessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", id));
        pessoaRepository.delete(pessoa);
    }
}
