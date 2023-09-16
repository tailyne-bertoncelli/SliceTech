package br.com.pizzaria.uniamerica.dto.saborDTOs;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
public class SaborDTO {
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private List<String> ingredientes;
}
