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
    @Getter
    @Setter
    private Long id;
    @Getter @Setter
    @NotBlank(message = "O logradouro não pode ser vazio ou em branco!")
    private String logradouro;
    @Getter @Setter
    private Long numero;
    @Getter @Setter
    @NotBlank(message = "O CEP não pode ser nullo ou vazio!")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "O cep deve seguir o formato XXXXX-XXX")
    private String cep;
    @Getter @Setter
    private String complemento;

    public EnderecoDTO(Long id, String logradouro, Long numero, String cep, String complemento) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
    }

    public EnderecoDTO(Endereco entity) {
        id = entity.getId();
        logradouro = entity.getLogradouro();
        numero = entity.getNumero();
        cep = entity.getCep();
        complemento = entity.getComplemento();
    }
}
