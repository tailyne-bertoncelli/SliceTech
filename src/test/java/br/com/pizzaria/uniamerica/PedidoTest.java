package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.PedidoController;
import br.com.pizzaria.uniamerica.controller.PizzaController;
import br.com.pizzaria.uniamerica.dto.pizzaDTOs.PizzaDTO;
import br.com.pizzaria.uniamerica.entities.*;
import br.com.pizzaria.uniamerica.repository.PedidosRepository;
import br.com.pizzaria.uniamerica.repository.PizzaRepository;
import br.com.pizzaria.uniamerica.repository.SaborRepository;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class PedidoTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private PizzaRepository pizzaRepository;

    @MockBean
    private PedidosRepository pedidosRepository;

    @Autowired
    private PedidoController pedidoController;

    @BeforeEach
    void obj(){
        Sabor sabor = new Sabor("Calabresa");
        sabor.setId(1L);

        Pizza pizza = new Pizza("Sem cebola", 149.90, sabor, TamanhoPizza.GIGANTE);
        pizza.setId(1L);
        pizza.setAtivo(true);

        Usuario usuario = new Usuario("login123","senha123","email123@gmail.com","CLIENTE");
        usuario.setId(1L);
        Endereco endereco = new Endereco("Avenida Brasil", 321L, "85862-570", "Esquina");
        endereco.setId(1L);
        endereco.setAtivo(true);
        Cliente cliente = new Cliente(usuario,endereco,"Ademar");
        cliente.setId(1L);

        Pedido pedido = new Pedido("DINHEIRO",pizza,sabor,"asdfasdf",24.50,true,false,cliente);


        List<Pedido> pedidoList = new ArrayList<>();
        pedidoList.add(new Pedido("DINHEIRO",pizza,sabor,"asdfasdf",24.50,true,false,cliente));
        pedidoList.add(new Pedido("DINHEIRO",pizza,sabor,"asdfasdf",24.50,true,false,cliente));
        pedidoList.add( new Pedido("DINHEIRO",pizza,sabor,"asdfasdf",24.50,true,false,cliente));



        Mockito.when(pedidosRepository.findById(1L)).thenReturn(Optional.of(pedido));
        Mockito.when(pedidosRepository.save(pedido)).thenReturn(pedido);
        Mockito.when(pedidosRepository.findAll()).thenReturn(pedidoList);
    }

    @Test
    void testFindById(){
        var pedido = pedidoController.findById(1L);
        Assert.assertEquals(23.5, pedido.getBody().getValor());
    }

//    @Test
//    void testCadastra(){
//        PizzaDTO pizzaDTO = new PizzaDTO();
//        pizzaDTO.setId(1L);
//        pizzaDTO.setSabor(1L);
//        pizzaDTO.setValor(59.90);
//        pizzaDTO.setTamanhoPizza(TamanhoPizza.GRANDE);
//        pizzaDTO.setDescricao("Adicionar extra de queijo");
//
//        var pizza = pizzaController.cadastra(pizzaDTO);
//
//        PizzaDTO pizzaDTOretorno = new PizzaDTO();
//        pizzaDTO.setId(pizza.getBody().getId());
//        pizzaDTO.setSabor(pizza.getBody().getSabor());
//        pizzaDTO.setValor(pizza.getBody().getValor());
//        pizzaDTO.setTamanhoPizza(pizza.getBody().getTamanhoPizza());
//        pizzaDTO.setDescricao(pizza.getBody().getDescricao());
//
//        Assert.assertEquals(pizzaDTOretorno.getId(), pizza.getBody().getId());
//    }
//
//    @Test
//    void testFindAll(){
//        var pizza = pizzaController.listaTodos();
//        Assert.assertEquals(3, pizza.getBody().size());
//    }
//
//    @Test
//    void testAltera(){
//        Sabor sabor = new Sabor("Frango");
//        sabor.setId(1L);
//
//        Pizza pizza = new Pizza("Sem cebola", 89.90, sabor,TamanhoPizza.GRANDE);
//        pizza.setId(1L);
//
//        var teste = pizzaController.altera(pizza);
//        PizzaDTO pizzaDTO = new PizzaDTO(pizza);
//
//        Assert.assertEquals(pizzaDTO.getId(), teste.getBody().getId());
//    }
//
//    @Test
//    void testDesativa(){
//        var pizza = pizzaController.desativa(1L);
//        Assert.assertEquals("A pizza foi desativada com sucesso!", pizza.getBody());
//    }
}
