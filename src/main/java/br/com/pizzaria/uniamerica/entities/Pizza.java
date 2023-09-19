package br.com.pizzaria.uniamerica.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pizza", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Pizza extends AbstractEntity {
    @Getter @Setter
    @Column(name = "decricao")
    private String descricao;

    @Getter @Setter
    @Column(name = "valor")
    private double valor;

    @Getter @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sabor", nullable = false)
    private Sabor sabor;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private TamanhoPizza tamanhoPizza;

    @OneToMany(mappedBy = "pizza")
    @Getter
    @Setter
    @JsonManagedReference
    private List<Pedido> pedidoList = new ArrayList<>();

    public Pizza(String descricao, double valor, Sabor sabor, TamanhoPizza tamanhoPizza) {
        this.descricao = descricao;
        this.valor = valor;
        this.sabor = sabor;
        this.tamanhoPizza = tamanhoPizza;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TamanhoPizza getTamanhoPizza() {
        return tamanhoPizza;
    }

    public void setTamanhoPizza(TamanhoPizza tamanhoPizza) {
        this.tamanhoPizza = tamanhoPizza;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }
}
