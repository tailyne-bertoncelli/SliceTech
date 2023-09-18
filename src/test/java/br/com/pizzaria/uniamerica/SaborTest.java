package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.SaborController;
import br.com.pizzaria.uniamerica.dto.saborDTOs.SaborDTO;
import br.com.pizzaria.uniamerica.entities.Sabor;
import br.com.pizzaria.uniamerica.repository.SaborRepository;
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
public class SaborTest {
    @MockBean
    private SaborRepository saborRepository;
    @Autowired
    private SaborController saborController;

    @BeforeEach
    void obj(){
        Sabor sabor = new Sabor("Calabresa");
        sabor.setId(1L);

        List<Sabor> saborList = new ArrayList<>();
        saborList.add(new Sabor("Frango"));
        saborList.add(new Sabor("Peperoni"));

        Mockito.when(saborRepository.save(sabor)).thenReturn(sabor);
        Mockito.when(saborRepository.findAll()).thenReturn(saborList);
        Mockito.when(saborRepository.findById(1L)).thenReturn(Optional.of(sabor));
    }

    @Test
    void testFindById(){
        Sabor sabor = new Sabor("Calabresa");
        sabor.setId(1L);

        var teste = saborController.buscaPeloId(1L);

        Assert.assertEquals(sabor.getNome(), teste.getBody().getNome());
    }

    @Test
    void testCadastra(){
        SaborDTO sabor = new SaborDTO();
        sabor.setNome("Italiana");
        sabor.setId(1L);

        var teste = saborController.cadastra(sabor);

        SaborDTO saborRetorno = new SaborDTO();
        sabor.setNome(teste.getBody().getNome());
        sabor.setId(teste.getBody().getId());

        Assert.assertEquals(sabor.getId(), saborRetorno.getId());
    }

    @Test
    void testAltera(){
        Sabor sabor = new Sabor("Portuguesa");
        sabor.setId(1L);

        var teste = saborController.altera(sabor);

        SaborDTO saborRetorno = new SaborDTO();
        sabor.setNome(teste.getBody().getNome());
        sabor.setId(teste.getBody().getId());

        Assert.assertEquals(sabor.getId(), saborRetorno.getId());
    }

    @Test
    void testFindAll(){
        var pizza = saborController.findAll();
        Assert.assertEquals(2, pizza.getBody().size());
    }

    @Test
    void testDesativa(){
        var pizza = saborController.desativar(1L);
        Assert.assertEquals("Sabor desativado com sucesso!", pizza.getBody());
    }
}
