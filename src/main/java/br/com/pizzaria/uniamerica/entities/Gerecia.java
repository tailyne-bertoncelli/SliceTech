package br.com.pizzaria.uniamerica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "tb_gerencia", schema = "public")
@Entity
public class Gerecia extends AbstractEntity{
    @Getter @Setter
    @Column(name = "relatoriosGerados")
    private LocalDate pedidosDoDia;

    @Getter @Setter
    @Column(name = "tipo_relatorio")
    private TipoRelatorio relatorio;
}
