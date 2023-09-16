package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {
}
