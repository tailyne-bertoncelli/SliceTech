package br.com.pizzaria.uniamerica.dto.clienteDTOs;


import br.com.pizzaria.uniamerica.dto.enderecoDTOs.EnderecoDTO;
import br.com.pizzaria.uniamerica.dto.pedidoDTOs.PedidoDTO;
import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ClienteDTO {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @NotNull
    @Column(name = "nome")
    private String nome;
    @Getter
    @Setter
    private UsuarioDTO usuarioDTO;
    @Getter
    @Setter
    private EnderecoDTO enderecoDTO;
    @Getter
    @Setter
    private List<PedidoDTO> pedidoList = new ArrayList<>();


    public ClienteDTO(Long id, String nome, UsuarioDTO usuarioDTO, EnderecoDTO enderecoDTO, List<PedidoDTO> pedidoList) {
        this.id = id;
        this.nome = nome;
        this.usuarioDTO = usuarioDTO;
        this.enderecoDTO = enderecoDTO;
        this.pedidoList = pedidoList;
    }

    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        enderecoDTO = new EnderecoDTO(entity.getEndereco());
        usuarioDTO = new UsuarioDTO(entity.getUsuario());

    }

}
