package br.com.pizzaria.uniamerica.dto.produtoDTOs;

import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.entities.Produto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
public class ProdutoDTO {
    @Getter @Setter
    private Long estoqueProduto_id;
    @Getter @Setter
    @Min(value = 1, message = "Informe a quantidade do produto!")
    private int quantidade;
    @Getter @Setter
    private double valorTotalProduto;

    public ProdutoDTO(Produto produto) {
        this.estoqueProduto_id = produto.getProduto().getId();
        this.quantidade = produto.getQuantidade();
        this.valorTotalProduto = produto.getValorTotalProduto();
    }

}
