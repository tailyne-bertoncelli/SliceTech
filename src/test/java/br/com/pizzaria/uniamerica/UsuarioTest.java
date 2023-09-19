package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.UsuarioController;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import br.com.pizzaria.uniamerica.service.UsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UsuarioTest {

    @MockBean
    private UsuarioRepository usuarioRepository;
    @MockBean
    private UsuarioService usuarioService;





}
