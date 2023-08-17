package br.com.pizzaria.uniamerica.dto.gerenciaDTOs;

import br.com.pizzaria.uniamerica.entities.TipoRelatorio;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class GerenciaDTO {

    @Past(message = "A data deve ser hoje ou passada!")
    private LocalDate pedidosDoDia;
    //private TipoRelatorio relatorio;
}
