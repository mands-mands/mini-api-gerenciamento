package com.br.uninter.atividade_pratica.repository;

import com.br.uninter.atividade_pratica.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
