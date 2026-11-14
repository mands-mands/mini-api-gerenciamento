package com.br.uninter.atividade_pratica.repository;

import com.br.uninter.atividade_pratica.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
