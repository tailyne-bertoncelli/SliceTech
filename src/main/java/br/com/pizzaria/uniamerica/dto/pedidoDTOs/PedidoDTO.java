package br.com.pizzaria.uniamerica.dto.pedidoDTOs;


import br.com.pizzaria.uniamerica.dto.clienteDTOs.ClienteDTO;
import br.com.pizzaria.uniamerica.dto.pizzaDTOs.PizzaDTO;
import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDTO;
import br.com.pizzaria.uniamerica.entities.Cliente;
import br.com.pizzaria.uniamerica.entities.Pizza;
import br.com.pizzaria.uniamerica.entities.Produto;
import br.com.pizzaria.uniamerica.entities.TamanhoPizza;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class PedidoDTO {
    @Getter
    @Setter
    private PizzaDTO pizza;
    @Getter
    @Setter
    private String sabor;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private double valor;
    @Getter
    @Setter
    private boolean entrega;
    @Getter
    @Setter

    private boolean situacao; //Possivel alteracao de nome por nao esclarecimento de nome de varaivel
    @Getter
    @Setter
    private ClienteDTO cliente;
    @Getter
    @Setter
    private List<ProdutoDTO> produtosList = new ArrayList<>();
}
