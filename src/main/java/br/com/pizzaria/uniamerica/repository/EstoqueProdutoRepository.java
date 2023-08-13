package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueProdutoRepository extends JpaRepository<EstoqueProduto, Long> {
}
