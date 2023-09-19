package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.UsuarioController;
import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.ClienteRepository;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClienteTest {
    @MockBean
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteRepository clienteRepository;

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

    @Test
    void testFindById(){
        var usuarioControllerVarEscopada = usuarioController.findById(1L);
        String loginUsuario = usuarioControllerVarEscopada.getBody().getLogin();
        Assert.assertEquals("login123", loginUsuario);
    }

    @Test
    void testRegister(){
        UsuarioDTO usuarioDTO = new UsuarioDTO(1L,"ZeDaManga","senha123","ZeDaManga@gmail.com","CLIENTE");
        usuarioDTO.setId(1L);
        var usuarioControllerVarEscopada = usuarioController.insert(usuarioDTO);
        Assert.assertEquals(usuarioDTO, usuarioControllerVarEscopada.getBody());
    }


    @Test
    void testFindAll(){
        var usuarioControllerVarEscopada = usuarioController.findAll();
        Assert.assertEquals(200, usuarioControllerVarEscopada.getStatusCode().value());
    }
}
