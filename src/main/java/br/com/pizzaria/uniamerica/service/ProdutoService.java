package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs.EstoqueProdutoDTO;
import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDTO;
import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDetalhesDTO;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.entities.Produto;
import br.com.pizzaria.uniamerica.repository.EstoqueProdutoRepository;
import br.com.pizzaria.uniamerica.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstoqueProdutoRepository estoqueProdutoRepository;
    private ModelMapper modelMapper;

    public ProdutoDTO findById(Long id){
        Produto produto = this.produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> findAll(){
        List<Produto> produtoList = this.produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOList = new ArrayList<>();
        for (Produto p: produtoList) {
            var prod = modelMapper.map(p, ProdutoDTO.class);
            produtoDTOList.add(prod);
        }
        return produtoDTOList;
    }

    @Transactional
    public ProdutoDTO cadastra(ProdutoDTO produtoDTO){
        EstoqueProduto estoqueProduto = this.estoqueProdutoRepository.findById(produtoDTO.getEstoqueProduto_id())
                .orElseThrow(()-> new RuntimeException("O produto informado não foi encontrado!"));

        verificaEstoque(estoqueProduto, produtoDTO);
        Produto produto = new Produto(estoqueProduto, produtoDTO.getQuantidade());

        double valorTotal = calculaTotalProduto(estoqueProduto, produtoDTO);
        produto.setValorTotalProduto(valorTotal);

        var prodSalvo = this.produtoRepository.save(produto);
        baixaEstoque(estoqueProduto, produto);
        return modelMapper.map(prodSalvo, ProdutoDTO.class);
    }

    @Transactional
    public ProdutoDetalhesDTO altera(Produto produto){
        Produto produtoAlterado = this.produtoRepository.findById(produto.getId())
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));

        produtoAlterado.setProduto(produto.getProduto());
        produtoAlterado.setQuantidade(produto.getQuantidade());

        this.produtoRepository.save(produtoAlterado);
        var estoqueProduto = modelMapper.map(produtoAlterado, EstoqueProdutoDTO.class);
        return new ProdutoDetalhesDTO(estoqueProduto, produto.getQuantidade(), produtoAlterado.getValorTotalProduto());
    }

    @Transactional
    public String desativa(Long id){
        Produto produto = this.produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        produto.setAtivo(false);
        this.produtoRepository.save(produto);
        return "Produto destivado com sucesso!";
    }

    private void verificaEstoque(EstoqueProduto estoqueProduto, ProdutoDTO produtoDTO) {
        if (estoqueProduto.getEstoque() == 0){
            throw new RuntimeException("Produto esgotado!");
        }
        if (produtoDTO.getQuantidade() > estoqueProduto.getEstoque()){
            throw new RuntimeException("Somente "+ estoqueProduto.getEstoque() + " " + estoqueProduto.getNome() +" em estoque!");
        }
    }

    private void baixaEstoque(EstoqueProduto estoqueProduto, Produto produto) {
        int estoque = estoqueProduto.getEstoque();
        int estoqueAtualizado = estoque - produto.getQuantidade();
        estoqueProduto.setEstoque(estoqueAtualizado);
    }

    private double calculaTotalProduto(EstoqueProduto estoqueProduto, ProdutoDTO produtoDTO) {
        double valorUn = estoqueProduto.getValorUnidade();
        int qntComprada = produtoDTO.getQuantidade();
        double total = valorUn * qntComprada;
        return total;
    }
}
