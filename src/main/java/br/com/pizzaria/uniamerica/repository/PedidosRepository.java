package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidosRepository extends JpaRepository<Pedido,Long> {
    @Modifying
    @Query("UPDATE Pedido pedido SET pedido .ativo=false WHERE pedido.id = :id")
    public void statusCancelado(@Param("id")Long id);
}
