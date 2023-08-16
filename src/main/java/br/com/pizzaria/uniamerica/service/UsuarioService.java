package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Produto;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import br.com.pizzaria.uniamerica.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Author: Cristovao Martins
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public UsuarioDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow( () -> new RuntimeException("Recurso não encontrado!"));
        return new UsuarioDTO(usuario);
    }

    public Page<UsuarioDTO> findAll(Pageable pageable){
        Page<Usuario> result = usuarioRepository.findAll(pageable);
        return result.map(x -> new UsuarioDTO(x));
    }

    @Transactional
    public UsuarioDTO insert(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        copyDtoToEntity(usuarioDTO,usuario);
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO update(Long id,UsuarioDTO usuarioDTO){
        try{
            Usuario usuario = usuarioRepository.getReferenceById(id);
            copyDtoToEntity(usuarioDTO,usuario);
            usuario = usuarioRepository.save(usuario);
            return new UsuarioDTO(usuario);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    private void copyDtoToEntity(UsuarioDTO usuarioDTO,Usuario usuario) {
       usuario.setLogin(usuarioDTO.getLogin());
       usuario.setEmail(usuarioDTO.getEmail());
       usuario.setSenha(usuarioDTO.getSenha());
    }
}
