package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDTO;
import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDetalhesDTO;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.entities.Produto;
import br.com.pizzaria.uniamerica.repository.EstoqueProdutoRepository;
import br.com.pizzaria.uniamerica.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstoqueProdutoRepository estoqueProdutoRepository;

    public ProdutoDTO findById(Long id){
        Optional<Produto> produto = this.produtoRepository.findById(id);
        produto.orElseThrow(()-> new RuntimeException("ID não encontrado!"));
        return new ProdutoDTO(produto);
    }

    public List<Produto> findAll(){
        List<Produto> produtoList = this.produtoRepository.findAll();
        return produtoList;
    }

    public ProdutoDetalhesDTO cadastra(ProdutoDTO produtoDTO){
        EstoqueProduto estoqueProduto = this.estoqueProdutoRepository.getReferenceById(produtoDTO.getEstoqueProduto_id());
        Produto produto = new Produto(estoqueProduto, produtoDTO.getQuantidade(), produtoDTO.getValorTotalProduto());
        this.produtoRepository.save(produto);
        return new ProdutoDetalhesDTO(produto.getProduto(), produto.getQuantidade(), produto.getValorTotalProduto());
    }

    public ProdutoDetalhesDTO altera(Produto produto){
        Produto produtoAlterado = this.produtoRepository.getReferenceById(produto.getId());
        this.produtoRepository.save(produtoAlterado);
        return new ProdutoDetalhesDTO(produto.getProduto(), produto.getQuantidade(), produtoAlterado.getValorTotalProduto());
    }

    public ProdutoDetalhesDTO desativa(Long id){
        Produto produto = this.produtoRepository.getReferenceById(id);
        produto.setAtivo(false);
        this.produtoRepository.save(produto);
        return new ProdutoDetalhesDTO(produto.getProduto(), produto.getQuantidade(), produto.getValorTotalProduto());
    }
}
