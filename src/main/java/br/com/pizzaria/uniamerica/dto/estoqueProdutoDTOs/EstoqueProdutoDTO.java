package br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs;

import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class EstoqueProdutoDTO {
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private double valor;
    @Getter @Setter
    private int estoque;

    public EstoqueProdutoDTO(Optional<EstoqueProduto> estoqueProduto) {
        this.nome = estoqueProduto.get().getNome();
        this.valor = estoqueProduto.get().getValor();
        this.estoque = estoqueProduto.get().getEstoque();
    }

    public EstoqueProdutoDTO(String nome, double valor, int estoque) {
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
    }
}
