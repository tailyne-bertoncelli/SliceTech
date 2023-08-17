package br.com.pizzaria.uniamerica.dto.enderecoDTOs;

import br.com.pizzaria.uniamerica.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
public class EnderecoDTO {
    @Getter @Setter
    @NotBlank(message = "O logradouro não pode ser vazio ou em branco!")
    private String logradouro;
    @Getter @Setter
    @NotBlank(message = "O numero não pode ser vazio ou em branco!")
    private int numero;
    @Getter @Setter
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "O cep deve seguir o formato XXXXX-XXX")
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
