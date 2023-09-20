package br.com.pizzaria.uniamerica.dto.pedidoDTOs;



import br.com.pizzaria.uniamerica.entities.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class PedidoDTO {
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private String formaPagamentoId;
    @Getter
    @Setter
    private Long pizzaId;
    @Getter
    @Setter
    @NotNull
    private String saborId;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    @NotNull
    private double valor;
    @Getter
    @Setter
    @NotNull
    private boolean entrega;
    @Getter
    @Setter
    @NotNull
    private boolean situacao; //Possivel alteracao de nome por nao esclarecimento de nome de varaivel
    @Getter
    @Setter
    @NotNull
    private Long clientId;

    public PedidoDTO(Pedido entity) {
       pizzaId = entity.getPizza().getId();
       clientId = entity.getCliente().getId();
       saborId = String.valueOf(entity.getSabor().getId());
       descricao = entity.getDescricao();
       valor = entity.getValor();
       entrega = entity.isEntrega();
       situacao = entity.isSituacao();
    }


}
