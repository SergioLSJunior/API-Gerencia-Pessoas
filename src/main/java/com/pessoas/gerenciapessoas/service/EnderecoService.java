package com.pessoas.gerenciapessoas.service;

import com.pessoas.gerenciapessoas.entity.Endereco;
import com.pessoas.gerenciapessoas.entity.Pessoa;
import com.pessoas.gerenciapessoas.repository.EnderecoRepository;
import com.pessoas.gerenciapessoas.repository.PessoaRepository;
import com.pessoas.gerenciapessoas.utils.BadRequestException;
import com.pessoas.gerenciapessoas.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Endereco> listarEnderecosDaPessoa(Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", pessoaId));
        return enderecoRepository.findByPessoaId(pessoa.getId());
    }

    public Endereco salvarEndereco(Long pessoaId, Endereco endereco) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", pessoaId));
        endereco.setPessoa(pessoa);
        return enderecoRepository.save(endereco);
    }

    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereco", "id", id));
    }

    public Endereco atualizarEndereco(Long id, Endereco enderecoAt) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco", "id", id));
        endereco.setLogradouro(enderecoAt.getLogradouro());
        endereco.setCep(enderecoAt.getCep());
        endereco.setNumero(enderecoAt.getNumero());
        endereco.setCidade(enderecoAt.getCidade());
        endereco.setEnderecoPrincipal(enderecoAt.getEnderecoPrincipal());
        return enderecoRepository.save(endereco);
    }

    public void deletarEndereco(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco", "id", id));
        enderecoRepository.delete(endereco);
    }

    public Endereco atualizarEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", pessoaId));
        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco", "id", enderecoId));
        if(endereco.getPessoa().getId() != pessoa.getId()) {
            throw new BadRequestException("Endereco não pertence a pessoa");
        }
        for(Endereco end : pessoa.getEnderecos()) {
            end.setEnderecoPrincipal(false);
            enderecoRepository.save(end);
        }
        endereco.setEnderecoPrincipal(true);
        return enderecoRepository.save(endereco);
    }

}