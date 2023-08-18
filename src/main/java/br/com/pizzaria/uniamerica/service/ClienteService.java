package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.clienteDTOs.ClienteDTO;
import br.com.pizzaria.uniamerica.entities.Cliente;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.entities.Produto;
import br.com.pizzaria.uniamerica.repository.ClienteRepository;
import br.com.pizzaria.uniamerica.repository.EnderecoRepository;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import br.com.pizzaria.uniamerica.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Cliente findById(Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cliente não encontrado!"));
        return cliente;
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente insert(ClienteDTO clienteDTO){
        var usuario = usuarioRepository.findById(clienteDTO.getUsuarioId()).orElseThrow(null);
        var endereco = enderecoRepository.findById(clienteDTO.getUsuarioId()).orElseThrow(null);

        Cliente cliente = new Cliente(usuario,endereco,clienteDTO.getNome());
        this.clienteRepository.save(cliente);
        return  cliente;
    }

//    @Transactional
//    public ClienteDTO update(Long id,ClienteDTO clienteDTO){
//        try{
//            var usuario = usuarioRepository.findById(clienteDTO.getUsuarioDTO().getId()).orElseThrow(null);
//            var endereco = enderecoRepository.findById(clienteDTO.getEnderecoDTO().getId()).orElseThrow(null);
//            Cliente cliente = clienteRepository.getReferenceById(id);
//            copyDtoToEntity(clienteDTO,usuario,endereco);
//            cliente = clienteRepository.save(cliente);
//            return new ClienteDTO(cliente);
//        }catch (EntityNotFoundException e){
//            throw new ResourceNotFoundException("Erro , Cliente não encontrado!");
//        }
//    }


//    private Cliente copyDtoToEntity(ClienteDTO clienteDTO) {
//        Cliente cliente = new Cliente();
//        cliente.setNome(clienteDTO.getNome());
//
//        cliente.setId(clienteDTO.getId());
//
//    }
}
