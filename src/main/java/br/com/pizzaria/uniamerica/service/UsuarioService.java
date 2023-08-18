package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import br.com.pizzaria.uniamerica.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
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
       usuario.setCargo(usuarioDTO.getCargo());

    }
}
