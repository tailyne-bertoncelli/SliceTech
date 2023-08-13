package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
