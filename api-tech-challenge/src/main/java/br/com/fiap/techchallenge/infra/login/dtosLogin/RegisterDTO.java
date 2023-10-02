package br.com.fiap.techchallenge.infra.login.dtosLogin;

import br.com.fiap.techchallenge.infra.login.UserRole;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
){

}
