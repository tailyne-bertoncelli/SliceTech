package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.clienteDTOs.ClienteDTO;
import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Cliente;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.ClienteRepository;
import br.com.pizzaria.uniamerica.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cliente não encontrado!"));
        return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAll(Pageable pageable){
        Page<Cliente> result = clienteRepository.findAll(pageable);
        return result.map(x -> new ClienteDTO(x));
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        copyDtoToEntity(clienteDTO,cliente);
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO update(Long id,ClienteDTO clienteDTO){
        try{
            Cliente cliente = clienteRepository.getReferenceById(id);
            copyDtoToEntity(clienteDTO,cliente);
            cliente = clienteRepository.save(cliente);
            return new ClienteDTO(cliente);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Erro , Cliente não encontrado!");
        }
    }


    private void copyDtoToEntity(ClienteDTO clienteDTO, Cliente cliente) {
        cliente.setNome(clienteDTO.getNome());

    }
}
