package br.com.pizzaria.uniamerica.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "tb_clientes",schema = "public")
public class Cliente extends AbstractEntity{
    @Getter
    @Setter
    @OneToOne
    @PrimaryKeyJoinColumn(name = "usuario_id")
    private Usuario usuario;
//    @Getter
//    @Setter
//    @ManyToOne
//    @JoinColumn(name = "endereco_id")
//    private Endereco endereco;
    @Getter
    @Setter
    @Column(name = "nome")
    private String nome;
}
