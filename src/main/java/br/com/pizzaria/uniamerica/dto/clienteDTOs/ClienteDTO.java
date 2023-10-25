package br.com.pizzaria.uniamerica.dto.clienteDTOs;


import br.com.pizzaria.uniamerica.dto.pedidoDTOs.PedidoDTO;
import br.com.pizzaria.uniamerica.entities.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ClienteDTO {
    @Getter @Setter
    private Long id;

    @Getter
    @Setter
    @NotNull
    private String nome;
    @Getter
    @Setter
    private int idade;

    @Getter
    @Setter
    private String telefone;

    @Getter
    @Setter
    private String genero;

    @Getter
    @Setter
    private String data_nascimento;
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

    public ClienteDTO(Long id, String nome, int idade, String telefone, String genero, String data_nascimento, Long usuarioId, Long enderecoId) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.genero = genero;
        this.data_nascimento = data_nascimento;
        this.usuarioId = usuarioId;
        this.enderecoId = enderecoId;
    }

    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        idade = entity.getIdade();
        telefone = entity.getTelefone();
        genero = entity.getGenero();
        data_nascimento = entity.getData_nascimento();
        usuarioId = entity.getUsuario().getId();
        enderecoId = entity.getEndereco().getId();
    }

    public ClienteDTO(String nome, Long usuarioId, Long enderecoId) {
        this.nome = nome;
        this.usuarioId = usuarioId;
        this.enderecoId = enderecoId;
    }
}
