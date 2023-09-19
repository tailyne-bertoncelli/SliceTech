package br.com.pizzaria.uniamerica.dto.produtoDTOs;

import br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs.EstoqueProdutoDTO;


public class ProdutoDetalhesDTO {
    private EstoqueProdutoDTO estoqueProduto;
    private int quantidade;
    private double valorTotalDoProduto;

    public ProdutoDetalhesDTO(EstoqueProdutoDTO estoqueProduto, int quantidade, double valorTotalProduto) {
        this.estoqueProduto = estoqueProduto;
        this.quantidade = quantidade;
        this.valorTotalDoProduto = valorTotalProduto;
    }
}
