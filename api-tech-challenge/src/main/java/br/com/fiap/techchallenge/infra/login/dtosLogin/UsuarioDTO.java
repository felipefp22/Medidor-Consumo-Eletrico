package br.com.fiap.techchallenge.infra.login.dtosLogin;

import br.com.fiap.techchallenge.domain.entidade.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioDTO(

        @JsonProperty
        String login,
        @JsonProperty
        String password
) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getUsername(), usuario.getPassword());
    }
}
