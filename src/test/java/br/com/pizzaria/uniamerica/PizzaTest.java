package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.PizzaController;
import br.com.pizzaria.uniamerica.dto.pizzaDTOs.PizzaDTO;
import br.com.pizzaria.uniamerica.entities.Pizza;
import br.com.pizzaria.uniamerica.entities.Sabor;
import br.com.pizzaria.uniamerica.entities.TamanhoPizza;
import br.com.pizzaria.uniamerica.repository.PizzaRepository;
import br.com.pizzaria.uniamerica.repository.SaborRepository;
import br.com.pizzaria.uniamerica.service.PizzaService;
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
public class PizzaTest {
    @MockBean
    private PizzaRepository pizzaRepository;
    @MockBean
    private SaborRepository saborRepository;
    @Autowired
    private PizzaController pizzaController;
    @Autowired
    private PizzaService pizzaService;

    @BeforeEach
    void obj(){
        Sabor sabor = new Sabor("Calabresa");
        sabor.setId(1L);
        Mockito.when(saborRepository.findById(1L)).thenReturn(Optional.of(sabor));

        Pizza pizza = new Pizza("Sem cebola", 149.90, sabor, TamanhoPizza.GIGANTE);
        pizza.setId(1L);
        pizza.setAtivo(true);

        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza("Sem cebola", 149.90, sabor, TamanhoPizza.PEQUENA));
        pizzaList.add(new Pizza("Sem tomate", 89.90, sabor, TamanhoPizza.MÉDIA));
        pizzaList.add(new Pizza("Sem queijo", 49.90, sabor, TamanhoPizza.PEQUENA));

        Mockito.when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));
        Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza);
        Mockito.when(pizzaRepository.findAll()).thenReturn(pizzaList);
    }

    @Test
    void testFindById(){
        var pizza = pizzaController.buscaPeloId(1L);
        Assert.assertEquals(1L, pizza.getBody().getId(), 0);
    }

    @Test
    void testCadastra(){
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(1L);
        pizzaDTO.setSabor(1L);
        pizzaDTO.setValor(59.90);
        pizzaDTO.setTamanhoPizza(TamanhoPizza.GRANDE);
        pizzaDTO.setDescricao("Adicionar extra de queijo");

        var pizza = pizzaService.cadastra(pizzaDTO);

        PizzaDTO pizzaDTOretorno = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setSabor(pizza.getSabor());
        pizzaDTO.setValor(pizza.getValor());
        pizzaDTO.setTamanhoPizza(pizza.getTamanhoPizza());
        pizzaDTO.setDescricao(pizza.getDescricao());

        Assert.assertEquals(pizzaDTOretorno.getId(), pizza.getId());
    }


}
