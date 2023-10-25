package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.clienteDTOs.ClienteDTO;
import br.com.pizzaria.uniamerica.entities.*;
import br.com.pizzaria.uniamerica.repository.ClienteRepository;
import br.com.pizzaria.uniamerica.repository.EnderecoRepository;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import br.com.pizzaria.uniamerica.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    public ClienteDTO findById(Long id){
        Cliente cliente = this.clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        return copyToDto(cliente);
    }


    public List<ClienteDTO> findAll(){
        List<Cliente> clienteList = this.clienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        for (Cliente c: clienteList) {
            if (c.isAtivo()) {
                var cliente = copyToDto(c);
                clienteDTOS.add(cliente);
            }
        }
        return clienteDTOS;
    }


    public ClienteDTO insert(ClienteDTO clienteDTO){
        Usuario usuario = this.usuarioRepository.findById(clienteDTO.getUsuarioId())
                .orElseThrow(()-> new RuntimeException("Usuario não encontrado!"));

        Endereco endereco = this.enderecoRepository.findById(clienteDTO.getEnderecoId())
                .orElseThrow(()-> new RuntimeException("Endereco não encontrado!"));


        Cliente cliente = new Cliente(usuario,endereco,clienteDTO.getNome(), clienteDTO.getIdade(), clienteDTO.getTelefone(), clienteDTO.getGenero(), clienteDTO.getData_nascimento());
        this.clienteRepository.save(cliente);
        return copyToDto(cliente);
    }

    @Transactional
    public ClienteDTO update(Cliente cliente){
        Cliente cliente1 = this.clienteRepository.findById(cliente.getId())
                .orElseThrow(()-> new RuntimeException("Cliente informado não foi encontrado!"));


        cliente1.setId(cliente.getId());
        cliente1.setNome(cliente.getNome());
        cliente1.setIdade(cliente.getIdade());
        cliente1.setTelefone(cliente.getTelefone());
        cliente1.setGenero(cliente.getGenero());
        cliente1.setData_nascimento(cliente.getData_nascimento());
        cliente1.setUsuario(cliente.getUsuario());
        cliente1.setEndereco(cliente.getEndereco());
        cliente1.setPedidoList(cliente.getPedidoList());


        this.clienteRepository.save(cliente1);
        return copyToDto(cliente1);
    }

    @Transactional
    public String logicDelete(Long id){
        Cliente cliente = this.clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID do cliente informado não foi encontrado!"));
        cliente.setAtivo(false);
        this.clienteRepository.save(cliente);
        return "Cliente desativado com sucesso!";
    }


    private ClienteDTO copyToDto(Cliente cliente) {
        return new ClienteDTO(cliente);
    }
}
