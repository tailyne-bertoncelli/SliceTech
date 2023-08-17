package br.com.pizzaria.uniamerica.dto.pizzaDTOs;

import br.com.pizzaria.uniamerica.entities.Pizza;
import br.com.pizzaria.uniamerica.entities.TamanhoPizza;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
public class PizzaDTO {
    @Getter @Setter
    private String sabor;
    @Getter @Setter
    private String descricao;
    @Getter @Setter
    private double valor;
    @Getter @Setter
    private TamanhoPizza tamanhoPizza;

    public PizzaDTO(Optional<Pizza> pizza) {
        this.sabor = pizza.get().getSabor();
        this.descricao = pizza.get().getDescricao();
        this.valor = pizza.get().getValor();
        this.tamanhoPizza = pizza.get().getTamanhoPizza();
    }

    public PizzaDTO(Pizza pizza1) {
        this.sabor = pizza1.getSabor();
        this.descricao = pizza1.getDescricao();
        this.valor = pizza1.getValor();
        this.tamanhoPizza = pizza1.getTamanhoPizza();
    }
}
