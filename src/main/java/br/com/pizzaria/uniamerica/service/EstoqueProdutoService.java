package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs.EstoqueProdutoDTO;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.repository.EstoqueProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueProdutoService {
    @Autowired
    private EstoqueProdutoRepository repository;

    public EstoqueProdutoDTO findById(Long id){
        EstoqueProduto estoqueProduto = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        return new EstoqueProdutoDTO(estoqueProduto);
    }

    public List<EstoqueProduto> findAll(){
        List<EstoqueProduto> listaTodos = repository.findAll();
        return listaTodos;
    }

    @Transactional
    public EstoqueProdutoDTO cadastra(EstoqueProdutoDTO estoqueProdutoDTO){
        EstoqueProduto estoqueProduto = new EstoqueProduto(estoqueProdutoDTO);
        this.repository.save(estoqueProduto);
        return estoqueProdutoDTO;
    }

    @Transactional
    public EstoqueProdutoDTO altera(EstoqueProduto estoqueProduto){
        EstoqueProduto produtoAlterado = repository.findById(estoqueProduto.getId())
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));

        produtoAlterado.setNome(estoqueProduto.getNome());
        produtoAlterado.setValorUnidade(estoqueProduto.getValorUnidade());
        produtoAlterado.setEstoque(estoqueProduto.getEstoque());

        this.repository.save(produtoAlterado);
        return new EstoqueProdutoDTO(produtoAlterado.getNome(),produtoAlterado.getValorUnidade(), produtoAlterado.getEstoque());
    }

    @Transactional
    public void desativa(Long id){
        EstoqueProduto estoqueProduto = this.repository.findById(id)
                        .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        estoqueProduto.setAtivo(false);
    }

}
