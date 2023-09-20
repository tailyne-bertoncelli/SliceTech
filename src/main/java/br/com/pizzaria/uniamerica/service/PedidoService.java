package br.com.pizzaria.uniamerica.service;


import br.com.pizzaria.uniamerica.dto.pedidoDTOs.PedidoDTO;
import br.com.pizzaria.uniamerica.dto.pizzaDTOs.PizzaDTO;
import br.com.pizzaria.uniamerica.entities.*;
import br.com.pizzaria.uniamerica.repository.ClienteRepository;
import br.com.pizzaria.uniamerica.repository.PedidosRepository;
import br.com.pizzaria.uniamerica.repository.PizzaRepository;
import br.com.pizzaria.uniamerica.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public PedidoDTO findById(Long id){
        Pedido pedido = this.pedidosRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        PedidoDTO pedidoDTO = copyToDto(pedido);
        return pedidoDTO;
    }


    public List<PedidoDTO> findAll(){
        List<Pedido> pedidoList = this.pedidosRepository.findAll();
        List<PedidoDTO> pedidoDTOS1 = new ArrayList<>();
        for (Pedido p: pedidoList) {
            var pizza = copyToDto(p);
            pedidoDTOS1.add(pizza);
        }
        return pedidoDTOS1;
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO pedidoDTO){

        Pedido pedido = new Pedido(pedidoDTO.getClientId(),pedidoDTO.getSaborId(),pedidoDTO.getDescricao(),pedidoDTO.getValor());
        this.pedidosRepository.save(pedido);
        PedidoDTO pedidoDTO1 = copyToDto(pedido);
        return pedidoDTO1;
    }

    @jakarta.transaction.Transactional
    public PedidoDTO update(Pedido pedido){
        Pedido pedido1 = this.pedidosRepository.findById(pedido.getId()).orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));

        pedido1.setSabor(pedido.getSabor());
        pedido1.setDescricao(pedido.getDescricao());
        pedido1.setValor(pedido.getValor());
        pedido1.setCliente(pedido.getCliente());
        pedido1.setPizza(pedido.getPizza());
        pedido1.setEntrega(pedido.isEntrega());
        pedido1.setSituacao(pedido.isSituacao());
        pedido1.setFormaDePagamento(pedido.getFormaDePagamento());


        this.pedidosRepository.save(pedido1);
        PedidoDTO pedidoDTO = copyToDto(pedido1);
        return pedidoDTO;
    }

    @Transactional
    public String logicDelete(Long id){
        Pedido pedido = this.pedidosRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        pedido.setAtivo(false);
        this.pedidosRepository.save(pedido);
        return "O Pedido foi desativado!";
    }


    private PedidoDTO copyToDto(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO(pedido);
        return pedidoDTO;
    }
}
