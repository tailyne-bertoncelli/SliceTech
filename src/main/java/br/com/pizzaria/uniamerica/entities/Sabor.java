package br.com.pizzaria.uniamerica.entities;

import br.com.pizzaria.uniamerica.dto.saborDTOs.SaborDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@NoArgsConstructor
@Table(name = "tb_sabor", schema = "public")
public class Sabor extends AbstractEntity{
    @Getter @Setter
    @Column(name = "nome")
    private String nome;
    @Getter @Setter
    @ElementCollection
    @Column(name = "Ingredientes")
    @CollectionTable(name = "tb_ingredientes", joinColumns = @JoinColumn(name = "sabor_id"))
    private List<String> ingredientes;

    public Sabor(SaborDTO saborDTO) {
        this.nome = saborDTO.getNome();
        this.ingredientes = saborDTO.getIngredientes();
    }
}

