package com.User.User.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record dadosUser(

        @NotNull
        String nome,

        @NotNull
        String telefone,

        @NotNull
        @Email
        String email,

        String endereco) {
}
