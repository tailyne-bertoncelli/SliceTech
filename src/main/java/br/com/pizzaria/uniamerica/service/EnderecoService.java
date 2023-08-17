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
        Optional<Endereco> enderecoDTO = this.enderecoRepository.findById(id);
        return new EnderecoDTO(enderecoDTO);
    }

    public List<Endereco> findAll(){
        List<Endereco> enderecoList = this.enderecoRepository.findAll();
        return enderecoList;
    }

    @Transactional
    public EnderecoDTO cadastra(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco(enderecoDTO);
        this.enderecoRepository.save(endereco);
        return enderecoDTO;
    }
    @Transactional
    public EnderecoDTO altera(Endereco endereco){
        Endereco endereco1 = this.enderecoRepository.getReferenceById(endereco.getId());

        endereco1.setLogradouro(endereco.getLogradouro());
        endereco1.setNumero(endereco.getNumero());
        endereco1.setCep(endereco.getCep());
        endereco1.setComplemento(endereco.getComplemento());

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
