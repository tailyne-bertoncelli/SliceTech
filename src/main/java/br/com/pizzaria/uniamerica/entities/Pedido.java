package br.com.pizzaria.uniamerica.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * Author : Cristovao Martins
 */
@Entity
@Table(name = "tb_pedidos",schema = "public")
@NoArgsConstructor
public class Pedido extends AbstractEntity{
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;
    @Getter
    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

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
    @Column(name = "situacao")
    private boolean situacao; //Possivel alteracao de nome por nao esclarecimento de nome de varaivel
    @Getter
    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    public Pedido(Cliente cliente, Pizza pizza, String formaPagamentoId, String descricao, double valor, boolean entrega, boolean situacao) {
        this.cliente = cliente;
        this.pizza = pizza;
        this.formaDePagamento = FormaDePagamento.valueOf(formaPagamentoId);
        this.descricao = descricao;
        this.valor = valor;
        this.entrega = entrega;
        this.situacao = situacao;
    }


}
