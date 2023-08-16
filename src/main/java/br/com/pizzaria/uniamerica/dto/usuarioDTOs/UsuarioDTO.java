package br.com.pizzaria.uniamerica.dto.usuarioDTOs;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UsuarioDTO {

    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String senha;
    @Getter
    @Setter
    private String email;

    public UsuarioDTO(String login, String senha, String email) {
        this.login = login;
        this.senha = senha;
        this.email = email;
    }
}
