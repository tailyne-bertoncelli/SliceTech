package br.com.pizzaria.uniamerica.entities;

import br.com.pizzaria.uniamerica.dto.enderecoDTOs.EnderecoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_endereco", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco extends AbstractEntity{
    @Getter @Setter
    @Column(name = "logradouro")
    private String logradouro;
    @Getter @Setter
    @Column(name = "numero")
    private Long numero;
    @Getter @Setter
    @Column(name = "cep")
    private String cep;
    @Getter @Setter
    @Column(name = "complemento")
    private String complemento;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.getLogradouro();
        this.numero = enderecoDTO.getNumero();
        this.cep = enderecoDTO.getCep();
        this.complemento = enderecoDTO.getComplemento();
    }
}
