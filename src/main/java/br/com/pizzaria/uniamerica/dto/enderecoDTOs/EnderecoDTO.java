package br.com.pizzaria.uniamerica.dto.enderecoDTOs;

import br.com.pizzaria.uniamerica.entities.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
public class EnderecoDTO {
    @Getter @Setter
    private String logradouro;
    @Getter @Setter
    private int numero;
    @Getter @Setter
    private String cep;
    @Getter @Setter
    private String complemento;

    public EnderecoDTO(Optional<Endereco> enderecoDTO) {
        this.logradouro = enderecoDTO.get().getLogradouro();
        this.numero = enderecoDTO.get().getNumero();
        this.cep = enderecoDTO.get().getCep();
        this.complemento = enderecoDTO.get().getComplemento();
    }

    public EnderecoDTO(Endereco endereco1) {
        this.logradouro = endereco1.getLogradouro();
        this.numero = endereco1.getNumero();
        this.cep = endereco1.getCep();
        this.complemento = endereco1.getComplemento();
    }
}
