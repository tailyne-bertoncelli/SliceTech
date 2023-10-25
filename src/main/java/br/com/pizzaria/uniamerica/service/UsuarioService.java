package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: Cristovao Martins
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO findById(Long id){
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        UsuarioDTO usuarioDTO = copyToDto(usuario);
        return usuarioDTO;
    }

    public List<UsuarioDTO> findAll(){
        List<Usuario> usuarioList = this.usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        for (Usuario users: usuarioList) {
            if (users.isAtivo()) {
                var user = copyToDto(users);
                usuarioDTOList.add(user);
            }
        }
        return usuarioDTOList;
    }

    @Transactional
    public UsuarioDTO cadastrar(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO.getLogin(),usuarioDTO.getSenha(),usuarioDTO.getEmail(),usuarioDTO.getCargo());
        this.usuarioRepository.save(usuario);
        usuarioDTO = copyToDto(usuario);
        return usuarioDTO;
    }

    @Transactional
    public UsuarioDTO alterar(Usuario usuario){
        Usuario usuario1 = this.usuarioRepository.findById(usuario.getId())
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));


        usuario1.setId(usuario.getId());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setLogin(usuario.getLogin());
        usuario1.setSenha(usuario.getSenha());
        usuario1.setCargo(usuario.getCargo());

        this.usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = copyToDto(usuario1);
        return usuarioDTO;
    }

    @Transactional
    public String desativar(Long id){
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        usuario.setAtivo(false);
        this.usuarioRepository.save(usuario);
        return "Usuario desativado!";
    }


    private UsuarioDTO copyToDto(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return usuarioDTO;
    }
}
