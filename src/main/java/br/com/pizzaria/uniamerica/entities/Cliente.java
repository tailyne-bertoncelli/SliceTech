package br.com.pizzaria.uniamerica.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: Cristovão Martins
 */
@NoArgsConstructor
@Entity
@Table(name = "tb_clientes",schema = "public")
public class Cliente extends AbstractEntity{
    @Getter
    @Setter
    @OneToOne
    @NotNull
    @PrimaryKeyJoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Getter
    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "endereco_id",nullable = false)
    private Endereco endereco;
    @Getter
    @Setter
    @Column(name = "nome")
    private String nome;

    @Getter
    @Setter
    @Column(name = "idade")
    private int idade;

    @Getter
    @Setter
    @Column(name = "telefone")
    private String telefone;

    @Getter
    @Setter
    @Column(name = "genero")
    private String genero;

    @Getter
    @Setter
    @Column(name = "data_nascimento")
    private String data_nascimento;

    @OneToMany(mappedBy = "cliente")
    @Getter
    @Setter
    @JsonManagedReference
    private List<Pedido> pedidoList = new ArrayList<>();

//    public Cliente(Usuario usuario, Endereco endereco, String nome) {
//        this.usuario = usuario;
//        this.endereco = endereco;
//        this.nome = nome;
//    }

    public Cliente(Usuario usuario, Endereco endereco, String nome, int idade, String telefone, String genero, String data_nascimento) {
        this.usuario = usuario;
        this.endereco = endereco;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.genero = genero;
        this.data_nascimento = data_nascimento;
    }
}
