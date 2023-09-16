package br.com.pizzaria.uniamerica.entities;

import br.com.pizzaria.uniamerica.dto.saborDTOs.SaborDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_sabor", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Sabor extends AbstractEntity{
    @Getter @Setter
    @Column(name = "nome")
    private String nome;

    public Sabor(SaborDTO saborDTO) {
        this.nome = saborDTO.getNome();
    }
}

