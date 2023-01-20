package com.pessoas.gerenciapessoas.repository;

import com.pessoas.gerenciapessoas.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}