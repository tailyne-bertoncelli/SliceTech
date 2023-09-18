package br.com.pizzaria.uniamerica;



import br.com.pizzaria.uniamerica.controller.ProdutoController;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.entities.Produto;
import br.com.pizzaria.uniamerica.repository.EstoqueProdutoRepository;
import br.com.pizzaria.uniamerica.repository.ProdutoRepository;
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
public class ProdutoTest {
    @MockBean
    private ProdutoRepository produtoRepository;
    @MockBean
    private EstoqueProdutoRepository estoqueProdutoRepository;

    @Autowired
    private ProdutoController produtoController;

    @BeforeEach
    void obj(){
        EstoqueProduto estoqueProduto = new EstoqueProduto("Coca cola 1L", 9.99, 10);
        estoqueProduto.setId(1L);
        Mockito.when(estoqueProdutoRepository.findById(1L)).thenReturn(Optional.of(estoqueProduto));

        Produto produto = new Produto(estoqueProduto, 2);
        produto.setId(1L);

        List<Produto> produtoList = new ArrayList<>();
        produtoList.add(new Produto(estoqueProduto, 2));
        produtoList.add(new Produto(estoqueProduto, 1));
        produtoList.add(new Produto(estoqueProduto, 3));

        Mockito.when(this.produtoRepository.save(produto)).thenReturn(produto);
        Mockito.when(this.produtoRepository.findAll()).thenReturn(produtoList);
        Mockito.when(this.produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
    }

//    @Test
//    public void findById(){
//        EstoqueProduto estoqueProduto = new EstoqueProduto("Coca cola 1L", 9.99, 10);
//        estoqueProduto.setId(1L);
//
//        Produto produto = new Produto(estoqueProduto, 2);
//        produto.setId(1L);
//
//        var teste = this.produtoController.buscaPeloId(1L);
//        Assert.assertEquals(1L, teste.getBody().getId(), 0);
//    }

    @Test
    public void findAll(){
        var teste = produtoController.listaTodos();
        Assert.assertEquals(3, teste.getBody().size());
    }
}
