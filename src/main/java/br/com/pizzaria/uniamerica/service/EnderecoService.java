package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.enderecoDTOs.EnderecoDTO;
import br.com.pizzaria.uniamerica.entities.Endereco;
import br.com.pizzaria.uniamerica.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO findById(Long id){
        Endereco enderecoDTO = this.enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
        return new EnderecoDTO(enderecoDTO);
    }

    public List<Endereco> findAll(){
        List<Endereco> enderecoList = this.enderecoRepository.findAll();
        return enderecoList;
    }

    @Transactional
    public EnderecoDTO cadastra(EnderecoDTO enderecoDTO){
        if (enderecoDTO.getNumero() == null || enderecoDTO.getNumero() == 0){
            throw new RuntimeException("O numero não pode ser 0 ou nullo");
        }
        Endereco endereco = new Endereco(enderecoDTO);
        this.enderecoRepository.save(endereco);
        return enderecoDTO;
    }
    @Transactional
    public EnderecoDTO altera(Endereco endereco){
        Endereco endereco1 = this.enderecoRepository.findById(endereco.getId())
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));

        endereco1.setLogradouro(endereco.getLogradouro());
        endereco1.setNumero(endereco.getNumero());
        endereco1.setCep(endereco.getCep());
        endereco1.setComplemento(endereco.getComplemento());

        if (endereco1.getNumero() == null || endereco1.getNumero() == 0){
            throw new RuntimeException("O numero não pode ser 0 ou nullo");
        }

        this.enderecoRepository.save(endereco1);
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco1);
        return enderecoDTO;
    }

    @Transactional
    public void desativa(Long id){
        Endereco endereco = this.enderecoRepository.getReferenceById(id);
        endereco.setAtivo(false);
        this.enderecoRepository.save(endereco);
    }
}
