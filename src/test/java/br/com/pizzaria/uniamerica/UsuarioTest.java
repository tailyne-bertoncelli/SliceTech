package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.UsuarioController;
import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import br.com.pizzaria.uniamerica.service.UsuarioService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UsuarioTest {

    @MockBean
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioController usuarioController;

    @BeforeEach
    void obj(){
        Usuario usuario = new Usuario("login123","senha123","email123@gmail.com","CLIENTE");
        usuario.setId(1L);

        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(new Usuario("Jose","senha123","email1@gmail.com","CLIENTE"));
        usuarioList.add(new Usuario("carlos","senha123","email2@gmail.com","CLIENTE"));
        usuarioList.add(new Usuario("Pedro","senha123","email3@gmail.com","CLIENTE"));

        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarioList);
    }

//    @Test
//    void testFindById(){
//        var usuarioControllerVarEscopada = usuarioController.findById(1L);
//        String loginUsuario = usuarioControllerVarEscopada.getBody().getLogin();
//        Assert.assertEquals("login123", loginUsuario);
//    }
//
//    @Test
//    void testeInsert(){
//        UsuarioDTO usuarioDTO = new UsuarioDTO(1L,"login123","senha123","email123@gmail.com","CLIENTE");
//
//        var usuarioControllerVarEscopada = usuarioController.Insert(usuarioDTO);
//
//        UsuarioDTO usuarioDTOCallback = new UsuarioDTO(usuarioControllerVarEscopada.getBody().getId(), usuarioControllerVarEscopada.getBody().getLogin(),usuarioControllerVarEscopada.getBody().getSenha(),usuarioControllerVarEscopada.getBody().getEmail(),usuarioControllerVarEscopada.getBody().getCargo());
//
//        Assert.assertEquals(usuarioDTOCallback.getId(),usuarioControllerVarEscopada.getBody().getId());
//    }
//
//    @Test
//    void findAll(){
//        var usuarioControllerVarEscopada = usuarioController.FindAll();
//
//        Assert.assertEquals(3,usuarioControllerVarEscopada.getBody().size());
//    }
//
//    @Test
//    void testUpdate(){
//
//    }
//
//    @Test
//    void testDisabled(){
//        var usuarioControllerVarEscopada = usuarioController.LogicDelete(1L);
//
//        Assert.assertEquals("Usuario desativado!", usuarioControllerVarEscopada.getBody());
//    }
//




}
