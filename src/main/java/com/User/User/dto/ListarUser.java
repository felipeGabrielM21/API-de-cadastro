package com.User.User.dto;

import com.User.User.model.User;

public record ListarUser(
        String nome,
        String email,
        String endereco,
        String telefone
) {
    public ListarUser(User user) {
        this(user.getNome(), user.getEmail(), user.getEndereco(), user.getTelefone());
    }
}

