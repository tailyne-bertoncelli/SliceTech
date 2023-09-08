package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.enderecoDTOs.EnderecoDTO;
import br.com.pizzaria.uniamerica.entities.Endereco;
import br.com.pizzaria.uniamerica.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    private ModelMapper modelMapper;

    public EnderecoDTO findById(Long id){
        Endereco endereco = this.enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));

        return modelMapper.map(endereco, EnderecoDTO.class);
    }

    public List<EnderecoDTO> findAll(){
        List<Endereco> enderecoList = this.enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
        for (Endereco e: enderecoList) {
            var end = modelMapper.map(e, EnderecoDTO.class);
            enderecoDTOList.add(end);
        }
        return enderecoDTOList;
    }

    @Transactional
    public EnderecoDTO cadastra(EnderecoDTO enderecoDTO){
        if (enderecoDTO.getNumero() == null || enderecoDTO.getNumero() == 0){
            throw new RuntimeException("O numero não pode ser 0 ou nullo");
        }
        var endereco = modelMapper.map(enderecoDTO, Endereco.class);
        this.enderecoRepository.save(endereco);
        return enderecoDTO;
    }
    @Transactional
    public EnderecoDTO altera(Endereco endereco){
        Endereco endereco1 = this.enderecoRepository.findById(endereco.getId())
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));

        if (endereco1.getNumero() == null || endereco1.getNumero() == 0){
            throw new RuntimeException("O numero não pode ser 0 ou nullo");
        }

        endereco1.setLogradouro(endereco.getLogradouro());
        endereco1.setNumero(endereco.getNumero());
        endereco1.setCep(endereco.getCep());
        endereco1.setComplemento(endereco.getComplemento());

        this.enderecoRepository.save(endereco1);
        var dtoRetorno = modelMapper.map(endereco1, EnderecoDTO.class);
        return dtoRetorno;
    }

    @Transactional
    public String desativa(Long id){
        Endereco endereco = this.enderecoRepository.getReferenceById(id);
        endereco.setAtivo(false);
        this.enderecoRepository.save(endereco);
        return "Endereço desativado com sucesso!";
    }
}
