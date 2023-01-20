package com.pessoas.gerenciapessoas.repository;

import com.pessoas.gerenciapessoas.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByPessoaId(Long idPessoa);
}