package com.br.uninter.atividade_pratica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank(message = "Nome não pode estar vazio")
        String nome,

        @NotNull(message = "O preço não pode estar vazio")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "O estoque não pode estar vazio")
        Boolean estoque
) {
}
