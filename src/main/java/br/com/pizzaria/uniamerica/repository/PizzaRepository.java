package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
