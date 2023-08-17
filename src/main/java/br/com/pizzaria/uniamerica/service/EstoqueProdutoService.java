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
        EstoqueProduto produtoAlterado = this.repository.getReferenceById(estoqueProduto.getId());

        produtoAlterado.setNome(estoqueProduto.getNome());
        produtoAlterado.setValor(estoqueProduto.getValor());
        produtoAlterado.setEstoque(estoqueProduto.getEstoque());

        this.repository.save(produtoAlterado);
        return new EstoqueProdutoDTO(produtoAlterado.getNome(),produtoAlterado.getValor(), produtoAlterado.getEstoque());
    }

    @Transactional
    public void desativa(Long id){
        Optional<EstoqueProduto> estoqueProduto = this.repository.findById(id);
        estoqueProduto.orElseThrow(()-> new RuntimeException("ID informado não foi encontrado!"));
        estoqueProduto.get().setAtivo(false);
    }

}
