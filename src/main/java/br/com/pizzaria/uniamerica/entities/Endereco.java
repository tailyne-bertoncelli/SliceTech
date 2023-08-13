package br.com.pizzaria.uniamerica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Endereco extends AbstractEntity{
    @Getter @Setter
    @Column(name = "logradouro")
    private String logradouro;
    @Getter @Setter
    @Column(name = "numero")
    private int numero;
    @Getter @Setter
    @Column(name = "cep")
    private String cep;
}
