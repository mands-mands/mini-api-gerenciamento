package com.br.uninter.atividade_pratica.dto.response;

import java.time.LocalDate;

public record ClienteResponseDTO(
        Long id,
        String nome,
        LocalDate clienteDesde
) {
}
