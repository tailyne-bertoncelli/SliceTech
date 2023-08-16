package br.com.pizzaria.uniamerica.dto.clienteDTOs;


import br.com.pizzaria.uniamerica.dto.pedidoDTOs.PedidoDTO;
import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Endereco;
import br.com.pizzaria.uniamerica.entities.Pedido;
import br.com.pizzaria.uniamerica.entities.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ClienteDTO {

    @Getter
    @Setter
    private UsuarioDTO usuario;
    @Getter
    @Setter
    private Endereco endereco;
    @Getter
    @Setter
    @Column(name = "nome")
    private String nome;
    @Getter
    @Setter
    private List<PedidoDTO> pedidoList = new ArrayList<>();


    public ClienteDTO(UsuarioDTO usuario, Endereco endereco, String nome, List<PedidoDTO> pedidoList) {
        this.usuario = usuario;
        this.endereco = endereco;
        this.nome = nome;
        this.pedidoList = pedidoList;
    }
}
