package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.saborDTOs.SaborDTO;
import br.com.pizzaria.uniamerica.entities.Sabor;
import br.com.pizzaria.uniamerica.repository.SaborRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaborService {
    @Autowired
    private SaborRepository saborRepository;

    private ModelMapper modelMapper;

    public SaborDTO findById(Long id){
        Sabor sabor = this.saborRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id não encontrado"));
        return modelMapper.map(sabor, SaborDTO.class);
    }

    public List<SaborDTO> findAll(){
        List<Sabor> list = this.saborRepository.findAll();
        List<SaborDTO> saborDTOList = new ArrayList<>();
        for (Sabor sabor: list) {
            var transformaEmDTO = modelMapper.map(sabor, SaborDTO.class);
            saborDTOList.add(transformaEmDTO);
        }
        return saborDTOList;
    }

    @Transactional
    public SaborDTO altera(Sabor sabor){
        Sabor saborAlterado = this.saborRepository.findById(sabor.getId())
                .orElseThrow(()-> new RuntimeException("Id não encontrado!"));
        this.saborRepository.save(saborAlterado);
        return modelMapper.map(saborAlterado, SaborDTO.class);
    }

    @Transactional
    public SaborDTO cadastra(SaborDTO saborDTO){
        Sabor sabor = new Sabor(saborDTO);
        this.saborRepository.save(sabor);
        return modelMapper.map(sabor, SaborDTO.class);
    }

    @Transactional
    public String desativa(Long id){
        Sabor sabor = this.saborRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id não encontrado!"));
        sabor.setAtivo(false);
        return "Sabor desativado com sucesso!";
    }
}
