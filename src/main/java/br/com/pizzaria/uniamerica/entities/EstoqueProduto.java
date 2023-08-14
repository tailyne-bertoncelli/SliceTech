package br.com.pizzaria.uniamerica.entities;

import br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs.EstoqueProdutoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_estoqueProduto", schema = "public")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueProduto extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome")
    private String nome;
    @Getter @Setter
    @Column(name = "valor")
    private double valor;
    @Getter @Setter
    @Column(name = "estoque")
    private int estoque;

    public EstoqueProduto(EstoqueProdutoDTO estoqueProdutoDTO) {
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
    }
}
