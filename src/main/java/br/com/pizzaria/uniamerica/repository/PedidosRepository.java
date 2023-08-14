package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedido,Long> {
}
