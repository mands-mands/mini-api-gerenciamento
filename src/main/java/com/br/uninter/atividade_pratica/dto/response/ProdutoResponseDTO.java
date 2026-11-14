package com.br.uninter.atividade_pratica.dto.response;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Boolean estoque
) {
}
