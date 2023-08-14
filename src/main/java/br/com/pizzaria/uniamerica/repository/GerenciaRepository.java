package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Gerecia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenciaRepository extends JpaRepository<Gerecia, Long> {
}
