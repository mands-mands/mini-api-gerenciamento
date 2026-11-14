package com.br.uninter.atividade_pratica.repository;

import com.br.uninter.atividade_pratica.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
