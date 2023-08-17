package br.com.pizzaria.uniamerica.dto.produtoDTOs;

import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.entities.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
public class ProdutoDTO {
    @Getter @Setter
    private Long estoqueProduto_id;
    @Getter @Setter
    private int quantidade;
    @Getter @Setter
    private double valorTotalProduto;

    public ProdutoDTO(Optional<Produto> produto) {
        this.estoqueProduto_id = produto.get().getProduto().getId();
        this.quantidade = produto.get().getQuantidade();
        this.valorTotalProduto = produto.get().getValorTotalProduto();
    }

    public ProdutoDTO(Long estoqueProduto_id, int quantidade, double valorTotalProduto) {
        this.estoqueProduto_id = estoqueProduto_id;
        this.quantidade = quantidade;
        this.valorTotalProduto = valorTotalProduto;
    }
}
