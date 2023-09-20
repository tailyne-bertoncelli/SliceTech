package br.com.pizzaria.uniamerica.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Author : Cristovao Martins
 */

@Entity
@Table(name = "tb_usuarios",schema = "public")
@NoArgsConstructor
public class Usuario extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "login")
    private String login;
    @Getter
    @Setter
    @Column(name = "senha")
    private String senha;
    @Getter
    @Setter
    @Column(name = "email",unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    @NotNull
    private Cargo cargo;

    public Usuario(String login, String senha, String email, String cargo) {
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.cargo = Cargo.valueOf(cargo);
    }
}
