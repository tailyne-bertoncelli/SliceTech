package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
