package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
