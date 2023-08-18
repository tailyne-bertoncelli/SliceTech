package br.com.pizzaria.uniamerica.entities;

import br.com.pizzaria.uniamerica.dto.enderecoDTOs.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_endereco", schema = "public")
@Builder
@NoArgsConstructor
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
    @OneToMany(mappedBy = "endereco")
    @JsonManagedReference
    private List<Cliente> clienteList = new ArrayList<>();

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.getLogradouro();
        this.numero = enderecoDTO.getNumero();
        this.cep = enderecoDTO.getCep();
        this.complemento = enderecoDTO.getComplemento();
    }
}
