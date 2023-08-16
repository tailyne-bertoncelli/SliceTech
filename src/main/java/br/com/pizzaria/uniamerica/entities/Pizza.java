package br.com.pizzaria.uniamerica.entities;

import br.com.pizzaria.uniamerica.dto.pizzaDTOs.PizzaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pizza", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Pizza extends AbstractEntity {
    @Getter @Setter
    @Column(name = "sabor")
    private String sabor;

    @Getter @Setter
    @Column(name = "decricao")
    private String descricao;

    @Getter @Setter
    @Column(name = "valor")
    private double valor;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private TamanhoPizza tamanhoPizza;

    public Pizza(PizzaDTO pizzaDTO) {
        this.sabor = pizzaDTO.getSabor();
        this.descricao = pizzaDTO.getDescricao();
        this.valor = pizzaDTO.getValor();
        this.tamanhoPizza = pizzaDTO.getTamanhoPizza();
    }
}
