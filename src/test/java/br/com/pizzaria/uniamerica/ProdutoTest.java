package br.com.pizzaria.uniamerica;


import br.com.pizzaria.uniamerica.controller.ProdutoController;
import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDTO;
import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDetalhesDTO;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.entities.Produto;
import br.com.pizzaria.uniamerica.repository.EstoqueProdutoRepository;
import br.com.pizzaria.uniamerica.repository.ProdutoRepository;
import br.com.pizzaria.uniamerica.service.ProdutoService;
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
class ProdutoTest {
    @MockBean
    private ProdutoRepository produtoRepository;
    @MockBean
    private EstoqueProdutoRepository estoqueProdutoRepository;

    @Autowired
    private ProdutoController produtoController;

    @Autowired
    private ProdutoService produtoService;

    @BeforeEach
    void obj(){
        EstoqueProduto estoqueProduto = new EstoqueProduto("Coca cola 1L", 9.99, 10);
        estoqueProduto.setId(1L);
        Mockito.when(estoqueProdutoRepository.findById(1L)).thenReturn(Optional.of(estoqueProduto));
        Mockito.when(estoqueProdutoRepository.save(estoqueProduto)).thenReturn(estoqueProduto);

        Produto produto = new Produto(estoqueProduto, 2);
        produto.setId(1L);

        List<Produto> produtoList = new ArrayList<>();
        produtoList.add(new Produto(estoqueProduto, 2));
        produtoList.add(new Produto(estoqueProduto, 1));
        produtoList.add(new Produto(estoqueProduto, 3));

        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);
        Mockito.when(produtoRepository.findAll()).thenReturn(produtoList);
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
    }

//    @Test
//    public void findById(){
//        EstoqueProduto estoqueProduto = new EstoqueProduto("Coca cola 1L", 9.99, 10);
//        estoqueProduto.setId(1L);
//
//        Produto produto = new Produto(estoqueProduto, 2);
//        produto.setId(1L);
//
//        var teste = this.produtoService.findById(1L);
//
//        ProdutoDTO produtoDTO = new ProdutoDTO();
//        produtoDTO.setId(teste.getId());
//        produtoDTO.setQuantidade(teste.getQuantidade());
//        produtoDTO.setEstoqueProduto_id(teste.getEstoqueProduto_id());
//
//        var recebe = 1L;
//
//        Assert.assertEquals(recebe, produtoDTO.getId().longValue());
//    }

    @Test
    void findAll(){
        var teste = produtoController.listaTodos();
        Assert.assertEquals(3, teste.getBody().size());
    }

//    @Test
//    void cadastra(){
//        EstoqueProduto estoqueProduto = new EstoqueProduto("Coca cola 1L", 9.99, 10);
//        estoqueProduto.setId(1L);
//
//        Produto produto = new Produto(estoqueProduto, 2);
//        produto.setId(1L);
//
//        ProdutoDTO produtoDTO = new ProdutoDTO();
//        produtoDTO.setId(1L);
//        produtoDTO.setEstoqueProduto_id(1L);
//        produtoDTO.setQuantidade(3);
//
//        var teste = produtoController.cadastra(produtoDTO);
//
//        ProdutoDTO produtoDTO1 = new ProdutoDTO();
//        produtoDTO1.setId(teste.getBody().getId());
//        produtoDTO1.setEstoqueProduto_id(teste.getBody().getEstoqueProduto_id());
//        produtoDTO1.setQuantidade(teste.getBody().getQuantidade());
//
//        Assert.assertEquals(produtoDTO1, teste.getBody());
//    }

    @Test
    void altera(){
        EstoqueProduto estoqueProduto = new EstoqueProduto("Coca cola 1L", 9.99, 10);
        estoqueProduto.setId(1L);

        Produto produto = new Produto(estoqueProduto, 2);
        produto.setId(1L);

        var teste = produtoController.altera(produto);

        ProdutoDetalhesDTO produtoDetalhesDTO = new ProdutoDetalhesDTO();
        produtoDetalhesDTO.setEstoqueProduto(teste.getBody().getEstoqueProduto());
        produtoDetalhesDTO.setQuantidade(teste.getBody().getQuantidade());
        produtoDetalhesDTO.setValorTotalDoProduto(teste.getBody().getValorTotalDoProduto());

        Assert.assertEquals(produtoDetalhesDTO.getEstoqueProduto(), teste.getBody().getEstoqueProduto());
    }

    @Test
    void desativa(){
        var teste = produtoController.desativa(1L);
        Assert.assertEquals("Produto destivado com sucesso!", teste.getBody());
    }

    @Test
    void calculaTotalProduto(){
        EstoqueProduto estoqueProduto = new EstoqueProduto("Coca cola 1L", 9.99, 10);
        estoqueProduto.setId(1L);

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setEstoqueProduto_id(1L);
        produtoDTO.setQuantidade(3);

        var teste = produtoService.calculaTotalProduto(estoqueProduto, produtoDTO);
        Assert.assertEquals(29.97, teste,0);
    }

}
