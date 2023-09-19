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
    @OneToMany(mappedBy = "cliente")
    @Getter
    @Setter
    @JsonManagedReference
    private List<Pedido> pedidoList = new ArrayList<>();

    public Cliente(Usuario usuario, Endereco endereco, String nome) {
        this.usuario = usuario;
        this.endereco = endereco;
        this.nome = nome;
    }


    public Cliente(Usuario usuario, Endereco endereco, String nome, List<Pedido> pedidoList) {
        this.usuario = usuario;
        this.endereco = endereco;
        this.nome = nome;
        this.pedidoList = pedidoList;
    }


}
