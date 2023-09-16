package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs.EstoqueProdutoDTO;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.repository.EstoqueProdutoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<EstoqueProdutoDTO> findAll(){
        List<EstoqueProduto> listaTodos = repository.findAll();
        List<EstoqueProdutoDTO> estoqueProdutoDTOList = new ArrayList<>();
        for (EstoqueProduto e: listaTodos) {
            EstoqueProdutoDTO estoqueProduto = copyToDto(e);
            estoqueProdutoDTOList.add(estoqueProduto);
        }
        return estoqueProdutoDTOList;
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
        EstoqueProdutoDTO dto = copyToDto(produtoAlterado);
        return dto;
    }

    @Transactional
    public String desativa(Long id){
        EstoqueProduto estoqueProduto = this.repository.findById(id)
                        .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        estoqueProduto.setAtivo(false);
        return "Produto do estoque desativado com sucesso!";
    }

    private EstoqueProdutoDTO copyToDto(EstoqueProduto e) {
        EstoqueProdutoDTO estoqueProdutoDTO = new EstoqueProdutoDTO(e);
        return estoqueProdutoDTO;
    }
}
