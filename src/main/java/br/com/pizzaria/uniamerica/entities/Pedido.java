package br.com.pizzaria.uniamerica.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "tb_pedidos",schema = "public")
@NoArgsConstructor
public class Pedido extends AbstractEntity{
//    @Getter
//    @Setter
//    private Tamanho tamanhoPizza;
//    @Getter
//    @Setter
//    private Pizza pizza;
    @Getter
    @Setter
    @Column(name = "sabor")
    private String sabor;
    @Getter
    @Setter
    @Column(name = "descricao")
    private String descricao;
    @Getter
    @Setter
    @Column(name = "valor")
    private double valor;
    @Getter
    @Setter
    @Column(name = "entrega")
    private boolean entrega;
    @Getter
    @Setter
    @Column(name = "sitaucao")
    private boolean situacao; //Possivel alteracao de nome por nao esclarecimento de nome de varaivel
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
//    @Getter
//    @Setter
//    private List<Produtos> produtosList = new ArrayList<>();
}
