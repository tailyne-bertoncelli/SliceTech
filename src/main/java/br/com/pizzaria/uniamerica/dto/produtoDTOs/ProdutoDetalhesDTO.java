package br.com.pizzaria.uniamerica.dto.produtoDTOs;

import br.com.pizzaria.uniamerica.entities.EstoqueProduto;

public class ProdutoDetalhesDTO {
    private EstoqueProduto estoqueProduto;
    private int quantidade;
    private double valorTotalDoProduto;

    public ProdutoDetalhesDTO(EstoqueProduto estoqueProduto, int quantidade, double valorTotalProduto) {
        this.estoqueProduto = estoqueProduto;
        this.quantidade = quantidade;
        this.valorTotalDoProduto = valorTotalProduto;
    }
}
