package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.UsuarioController;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import br.com.pizzaria.uniamerica.service.UsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioTest {

    @MockBean
    private UsuarioRepository usuarioRepository;
    @MockBean
    private UsuarioController usuarioController;


    void MockCadastrarInformações(){
        Usuario usuario = new Usuario("loginseguro123","senhasegura123","email@gmail.com","CLIENTE");
        usuario.setId(1L);
        usuario.setAtivo(true);

        Mockito.when(usuarioRepository.findAll()).thenReturn((List<Usuario>) usuario);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

    }

    @Test
    void testeFindById(){
        var expected = usuarioController.findById(1L);
        Assert.assertEquals(1L,expected.getBody().getId(),0);

    }
}
