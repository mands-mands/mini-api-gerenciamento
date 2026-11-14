package com.br.uninter.atividade_pratica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome não pode estar vazio")
        String nome,

        @NotNull(message = "A data não pode estar vazia")
        LocalDate clienteDesde
) {
}
