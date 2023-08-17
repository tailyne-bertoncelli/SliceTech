package br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs;

import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import lombok.Getter;
import lombok.Setter;

public class EstoqueProdutoDTO {
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private double valor;
    @Getter @Setter
    private int estoque;

    public EstoqueProdutoDTO(EstoqueProduto estoqueProduto) {
        this.nome = estoqueProduto.getNome();
        this.valor = estoqueProduto.getValor();
        this.estoque = estoqueProduto.getEstoque();
    }

    public EstoqueProdutoDTO(String nome, double valor, int estoque) {
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
    }
}
