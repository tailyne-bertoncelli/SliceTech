package br.com.pizzaria.uniamerica.dto.pizzaDTOs;

import br.com.pizzaria.uniamerica.entities.Pizza;
import br.com.pizzaria.uniamerica.entities.TamanhoPizza;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
public class PizzaDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    @NotBlank(message = "O sabor não pode ser null ou vazio!")
    private Long sabor;
    @Getter @Setter
    private String descricao;
    @Getter @Setter
    @DecimalMin(value = "0.01", message = "O valor não pode ser 0 ou null!")
    private double valor;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tamanho da pizza não pode ser null ou vazio!")
    private TamanhoPizza tamanhoPizza;

    public PizzaDTO(Pizza pizza1) {
        this.id = pizza1.getId();
        this.sabor = pizza1.getSabor().getId();
        this.descricao = pizza1.getDescricao();
        this.valor = pizza1.getValor();
        this.tamanhoPizza = pizza1.getTamanhoPizza();
    }
}
