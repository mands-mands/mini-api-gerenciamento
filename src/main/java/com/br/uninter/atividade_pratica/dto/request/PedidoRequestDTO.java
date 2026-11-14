package com.br.uninter.atividade_pratica.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PedidoRequestDTO(

        @NotNull(message = "Id do cliente não pode estar vazio")
        Long clienteId,

        @NotNull(message = "Id do produto não pode estar vazio")
        Long produtoId,

        @NotNull(message = "Quantidade não pode estar vazia")
        @Positive(message = "Quantidade deve ser maior que zero")
        Integer quantidade
) {
}
