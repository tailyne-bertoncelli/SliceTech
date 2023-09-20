package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.ClienteController;
import br.com.pizzaria.uniamerica.entities.Cliente;
import br.com.pizzaria.uniamerica.entities.Endereco;

import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.repository.ClienteRepository;

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
class ClienteTest {
    @MockBean
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteController clienteController;

    @BeforeEach
    void obj(){
        Usuario usuario = new Usuario("login123","senha123","email123@gmail.com","CLIENTE");
        usuario.setId(1L);
        Endereco endereco = new Endereco("Avenida Brasil", 321L, "85862-570", "Esquina");
        endereco.setId(1L);
        endereco.setAtivo(true);
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
    void testGetSetUsuario() {
        Usuario usuario = new Usuario();
        cliente.setUsuario(usuario);

        assertEquals(usuario, cliente.getUsuario());
    }

    @Test
    void testGetSetEndereco() {
        Endereco endereco = new Endereco();
        cliente.setEndereco(endereco);

        assertEquals(endereco, cliente.getEndereco());
    }

    @Test
    void testGetSetNome() {
        cliente.setNome("João da Silva");

        assertEquals("João da Silva", cliente.getNome());
    }

    @Test
    void testGetSetPedidoList() {
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();

        cliente.adicionarPedido(pedido1);
        cliente.adicionarPedido(pedido2);

        assertEquals(2, cliente.getPedidoList().size());
        assertTrue(cliente.getPedidoList().contains(pedido1));
        assertTrue(cliente.getPedidoList().contains(pedido2));
    }

    private void initCliente() {
        cliente = new Cliente();
        cliente.setUsuario(new Usuario());
        cliente.setEndereco(new Endereco());
        cliente.setNome("João da Silva");
    }
}
