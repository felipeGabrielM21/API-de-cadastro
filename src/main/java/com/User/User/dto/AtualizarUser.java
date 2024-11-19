package com.User.User.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarUser(

        @NotNull
        Long id,

        String telefone,

        String email,

        String endereco) {
}
