package br.com.pizzaria.uniamerica.dto.produtoDTOs;

import br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs.EstoqueProdutoDTO;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import lombok.Getter;
import lombok.Setter;

public class ProdutoDetalhesDTO {
    @Getter @Setter
    private EstoqueProdutoDTO estoqueProduto;
    @Getter @Setter
    private int quantidade;
    @Getter @Setter
    private double valorTotalDoProduto;

    public ProdutoDetalhesDTO(EstoqueProdutoDTO estoqueProduto, int quantidade, double valorTotalProduto) {
        this.estoqueProduto = estoqueProduto;
        this.quantidade = quantidade;
        this.valorTotalDoProduto = valorTotalProduto;
    }

    public ProdutoDetalhesDTO() {
    }
}
