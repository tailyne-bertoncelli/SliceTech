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
    @NotBlank(message = "O nome do produto não pode null ou em branco!")
    private String nome;
    @Getter @Setter
    @DecimalMin(value = "0.01", message = "O valor não pode ser 0 ou null")
    private double valor;
    @Getter @Setter
    @Min(value = 1, message = "O estoque não pode ser 0 ou null!")
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
