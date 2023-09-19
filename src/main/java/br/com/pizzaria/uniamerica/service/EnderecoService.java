package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.enderecoDTOs.EnderecoDTO;
import br.com.pizzaria.uniamerica.entities.Endereco;
import br.com.pizzaria.uniamerica.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;


    public EnderecoDTO findById(Long id){
        Endereco endereco = this.enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));

        EnderecoDTO dto = copyToDto(endereco);
        return dto;
    }


    public List<EnderecoDTO> findAll(){
        List<Endereco> enderecoList = this.enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
        for (Endereco e: enderecoList) {
            var end = copyToDto(e);
            enderecoDTOList.add(end);
        }
        return enderecoDTOList;
    }

    @Transactional
    public EnderecoDTO cadastra(EnderecoDTO enderecoDTO){
        if (enderecoDTO.getNumero() == null || enderecoDTO.getNumero() == 0){
            throw new RuntimeException("O numero não pode ser 0 ou nullo");
        }
        Endereco endereco = new Endereco(enderecoDTO);
        this.enderecoRepository.save(endereco);
        EnderecoDTO dto = copyToDto(endereco);
        return dto;
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
        EnderecoDTO dtoRetorno = copyToDto(endereco1);
        return dtoRetorno;
    }

    @Transactional
    public String desativa(Long id){
        Endereco endereco = this.enderecoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id não encontrado!"));
        endereco.setAtivo(false);
        this.enderecoRepository.save(endereco);
        return "Endereço desativado com sucesso!";
    }

    private EnderecoDTO copyToDto(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        return enderecoDTO;
    }
}
