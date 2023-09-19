package br.com.pizzaria.uniamerica.dto.clienteDTOs;



import br.com.pizzaria.uniamerica.dto.pedidoDTOs.PedidoDTO;

import br.com.pizzaria.uniamerica.entities.*;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClienteDTO {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @NotNull
    private String nome;
    @Getter
    @Setter
    private Long usuarioId;
    @Getter
    @Setter
    private Long enderecoId;
    @Getter
    @Setter
    private List<PedidoDTO> pedidoList = new ArrayList<>();

    public ClienteDTO( String nome, Long usuarioId, Long enderecoId, List<PedidoDTO> pedidoList) {
        this.nome = nome;
        this.usuarioId = usuarioId;
        this.enderecoId = enderecoId;
        this.pedidoList = pedidoList;
    }

    public ClienteDTO( String nome, Long usuarioId, Long enderecoId) {
        this.nome = nome;
        this.usuarioId = usuarioId;
        this.enderecoId = enderecoId;

    }

    public ClienteDTO(Cliente entity) {
        nome = entity.getNome();
        usuarioId = entity.getUsuario().getId();
        enderecoId = entity.getEndereco().getId();
    }

}
