package br.com.pizzaria.uniamerica.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

/**
 * Author : Cristovao Martins
 */

@MappedSuperclass
@NoArgsConstructor
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "id",nullable = false)
    private Long id;
    @Getter
    @Setter
    @Column(name = "ativo",nullable = false)
    private  boolean ativo;
    @Getter
    @Setter
    @Column(name = "cadastro",nullable = false)
    private LocalDate cadastro;
    @Getter
    @Setter
    @Column(name = "edicao",nullable = true)
    private LocalDate edicao;

    /**
     * Metodo utilizado Pré-Persistencia de dados no banco!
     */
    @PrePersist
    public void PrePersist(){
        this.cadastro = LocalDate.now();
        ativo = true;
    }

    /**
     *Metodo utilizado Pré-update no banco de dados!
     */
    @PreUpdate
    public void PreUpdate(){
        this.edicao = LocalDate.now();
    }
}
