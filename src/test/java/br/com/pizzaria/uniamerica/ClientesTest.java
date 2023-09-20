package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.ClienteController;
import br.com.pizzaria.uniamerica.dto.clienteDTOs.ClienteDTO;
import br.com.pizzaria.uniamerica.entities.Cliente;
import br.com.pizzaria.uniamerica.entities.Endereco;
import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.ClienteRepository;
import br.com.pizzaria.uniamerica.repository.EnderecoRepository;
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
class ClientesTest {
    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private UsuarioRepository usuarioRepository;
    @MockBean
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ClienteController clienteController;

    @BeforeEach
    void obj(){
        Usuario usuario = new Usuario("login123","senha123","email123@gmail.com","CLIENTE");
        usuario.setId(1L);
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Endereco endereco = new Endereco("Avenida Brasil", 321L, "85862-570", "Esquina");
        endereco.setId(1L);
        endereco.setAtivo(true);
        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

        Cliente cliente = new Cliente(usuario,endereco,"Ademar");
        cliente.setId(1L);

        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(new Cliente(usuario,endereco,"Taillyne"));
        clienteList.add(new Cliente(usuario,endereco,"Gabi"));
        clienteList.add(new Cliente(usuario,endereco,"Pedro"));


        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        Mockito.when(clienteRepository.findAll()).thenReturn(clienteList);
    }

    @Test
    void testFindById(){
        var clienteControllerVarEscopada = clienteController.findById(1L);
        String clienteNome = clienteControllerVarEscopada.getBody().getNome();
        Assert.assertEquals("Ademar", clienteNome);
    }



    @Test
    void findAll(){
        var clienteControllerVarEscopada = clienteController.findAll();

        Assert.assertEquals(3,clienteControllerVarEscopada.getBody().size());
    }


    @Test
    void testDisabled(){
        var clienteControllerVarEscopada = clienteController.deleteLogic(1L);

        Assert.assertEquals("Cliente desativado com sucesso!", clienteControllerVarEscopada.getBody());
    }

    @Test
    void testUpdate(){
        Usuario usuario = new Usuario("login123","senha123","email123@gmail.com","CLIENTE");
        usuario.setId(1L);
        Endereco endereco = new Endereco("Avenida Brasil", 321L, "85862-570", "Esquina");
        endereco.setId(1L);
        endereco.setAtivo(true);
        Cliente cliente = new Cliente(usuario,endereco,"Ademar");
        cliente.setId(1L);


        var variavelVarScopada = clienteController.update(cliente);
        ClienteDTO clienteDTO = new ClienteDTO(cliente);

        Assert.assertEquals(clienteDTO.getId(), variavelVarScopada.getBody().getId());
    }

    @Test
    void testeInsert(){
        ClienteDTO clienteDTO = new ClienteDTO("TESTE",1L,1L);
        clienteDTO.setId(1L);

        var usuarioControllerVarEscopada = clienteController.insert(clienteDTO);

        ClienteDTO clienteDTOCallback = new ClienteDTO(clienteDTO.getNome(),clienteDTO.getUsuarioId(),clienteDTO.getEnderecoId());
        clienteDTOCallback.setId(usuarioControllerVarEscopada.getBody().getId());
        Assert.assertEquals(clienteDTOCallback.getId() ,usuarioControllerVarEscopada.getBody().getId());
    }
}
