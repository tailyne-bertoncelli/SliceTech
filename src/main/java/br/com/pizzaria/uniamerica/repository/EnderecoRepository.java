package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
