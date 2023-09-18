package br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs;

import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class EstoqueProdutoDTO {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @NotBlank(message = "O nome do produto não pode null ou em branco!")
    private String nome;
    @Getter @Setter
    @DecimalMin(value = "0.01", message = "O valor não pode ser 0 ou null")
    private double valorUnidade;
    @Getter @Setter
    private int estoque;

    public EstoqueProdutoDTO(EstoqueProduto estoqueProduto) {
        this.nome = estoqueProduto.getNome();
        this.valorUnidade = estoqueProduto.getValorUnidade();
        this.estoque = estoqueProduto.getEstoque();
    }

    public EstoqueProdutoDTO(String nome, double valor, int estoque) {
        this.nome = nome;
        this.valorUnidade = valor;
        this.estoque = estoque;
    }

    public EstoqueProdutoDTO() {

    }
}
