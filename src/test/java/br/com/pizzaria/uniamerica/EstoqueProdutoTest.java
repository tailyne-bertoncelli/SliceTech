package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.EstoqueProdutoController;
import br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs.EstoqueProdutoDTO;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.repository.EstoqueProdutoRepository;
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
class EstoqueProdutoTest {
    @MockBean
    private EstoqueProdutoRepository estoqueProdutoRepository;
    @Autowired
    private EstoqueProdutoController estoqueProdutoController;

    @BeforeEach
    void obj(){
        EstoqueProduto estoqueProduto = new EstoqueProduto("Coca cola 1L", 9.99, 10);
        estoqueProduto.setId(1L);

        List<EstoqueProduto> estoqueProdutosList = new ArrayList<>();
        estoqueProdutosList.add(new EstoqueProduto("Pepsi 1L", 5.99, 5));
        estoqueProdutosList.add(new EstoqueProduto("Refrigerante 500ml", 4.99, 20));
        estoqueProdutosList.add(new EstoqueProduto("Refrigerante 2L", 10.99, 15));

        Mockito.when(estoqueProdutoRepository.save(estoqueProduto)).thenReturn(estoqueProduto);
        Mockito.when(estoqueProdutoRepository.findById(1L)).thenReturn(Optional.of(estoqueProduto));
        Mockito.when(estoqueProdutoRepository.findAll()).thenReturn(estoqueProdutosList);
    }

    @Test
    void testFindById(){
        var estoque = estoqueProdutoController.buscaPeloId(1L);
        String produto = estoque.getBody().getNome();
        Assert.assertEquals("Coca cola 1L", produto);
    }

    @Test
    void testCadastra(){
        EstoqueProdutoDTO estoqueProdutoDTO = new EstoqueProdutoDTO("Coca cola 1L", 9.99, 10);
        estoqueProdutoDTO.setId(1L);
        var teste = estoqueProdutoController.cadastra(estoqueProdutoDTO);
        Assert.assertEquals(estoqueProdutoDTO, teste.getBody());
    }

    @Test
    void testAltera(){
        EstoqueProduto estoqueProduto = new EstoqueProduto("Coca cola 1L", 9.99, 10);
        estoqueProduto.setId(1L);

        var teste = estoqueProdutoController.altera(estoqueProduto);

        EstoqueProdutoDTO estoqueProdutoDTO = new EstoqueProdutoDTO();
        estoqueProdutoDTO.setId(teste.getBody().getId());
        estoqueProdutoDTO.setNome(teste.getBody().getNome());
        estoqueProdutoDTO.setEstoque(teste.getBody().getEstoque());
        estoqueProdutoDTO.setValorUnidade(teste.getBody().getValorUnidade());

        Assert.assertEquals(estoqueProdutoDTO.getId(), teste.getBody().getId());
    }

    @Test
    void testFindAll(){
        var teste = estoqueProdutoController.listaTodos();
        Assert.assertEquals(3, teste.getBody().size());
    }

    @Test
    void testDesativa(){
        var teste = estoqueProdutoController.desativa(1L);
        Assert.assertEquals("Produto do estoque desativado com sucesso!", teste.getBody());
    }
}