package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.pedidoDTOs.PedidoDTO;
import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Pedido;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.PedidosRepository;
import br.com.pizzaria.uniamerica.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
    @Autowired
    private PedidosRepository pedidosRepository;

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        Pedido pedido = pedidosRepository.findById(id).orElseThrow( () -> new RuntimeException("Recurso não encontrado!"));
        return new PedidoDTO(pedido);
    }

    @Transactional(readOnly = true)
    public Page<PedidoDTO> findAll(Pageable pageable){
        Page<Pedido> result = pedidosRepository.findAll(pageable);
        return result.map(x -> new PedidoDTO(x));
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido();
        copyDtoToEntity(pedidoDTO,pedido);
        pedido = pedidosRepository.save(pedido);
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO update(Long id,PedidoDTO pedidoDTO){
        try{
            Pedido pedido = pedidosRepository.getReferenceById(id);
            copyDtoToEntity(pedidoDTO,pedido);
            pedido = pedidosRepository.save(pedido);
            return new PedidoDTO(pedido);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    private void copyDtoToEntity(PedidoDTO pedidoDTO, Pedido pedido) {
        pedido.setSabor(pedidoDTO.getSabor());
        pedido.setDescricao(pedidoDTO.getDescricao());
        pedido.setValor(pedidoDTO.getValor());
        pedido.setEntrega(pedidoDTO.isEntrega());
        pedido.setSituacao(pedidoDTO.isSituacao());
    }
}
