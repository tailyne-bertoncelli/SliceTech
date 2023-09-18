package br.com.pizzaria.uniamerica.dto.saborDTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class SaborDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String nome;
}
