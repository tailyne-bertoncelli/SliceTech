package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.clienteDTOs.ClienteDTO;
import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Cliente;
import br.com.pizzaria.uniamerica.entities.Endereco;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.ClienteRepository;
import br.com.pizzaria.uniamerica.repository.EnderecoRepository;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import br.com.pizzaria.uniamerica.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cliente não encontrado!"));
        return new ClienteDTO(cliente);
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO clienteDTO){
        var usuario = usuarioRepository.findById(clienteDTO.getUsuarioDTO().getId()).orElseThrow(null);
        var endereco = enderecoRepository.findById(clienteDTO.getEnderecoDTO().getId()).orElseThrow(null);
        var cliente = copyDtoToEntity(clienteDTO,usuario,endereco);
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO update(Long id,ClienteDTO clienteDTO){
        try{
            var usuario = usuarioRepository.findById(clienteDTO.getUsuarioDTO().getId()).orElseThrow(null);
            var endereco = enderecoRepository.findById(clienteDTO.getEnderecoDTO().getId()).orElseThrow(null);
            Cliente cliente = clienteRepository.getReferenceById(id);
            copyDtoToEntity(clienteDTO,usuario,endereco);
            cliente = clienteRepository.save(cliente);
            return new ClienteDTO(cliente);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Erro , Cliente não encontrado!");
        }
    }


    private Cliente copyDtoToEntity(ClienteDTO clienteDTO,Usuario usuario,Endereco endereco) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setId(clienteDTO.getId());
        cliente.setUsuario(usuario);
        cliente.setEndereco(endereco);

        return cliente;
    }
}
