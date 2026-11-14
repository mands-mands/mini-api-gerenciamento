package com.br.uninter.atividade_pratica.dto.response;

public record PedidoResponseDTO(
        Long id,
        Long clienteId,
        String clienteNome,
        Long produtoId,
        String produtoNome,
        Integer quantidade
) {
}
