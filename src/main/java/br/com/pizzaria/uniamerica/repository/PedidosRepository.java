package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido,Long> {
    @Modifying
    @Query("UPDATE Pedido pedido SET pedido .ativo=false WHERE pedido.id = :id")
    public void statusCancelado(@Param("id")Long id);

    @Modifying
    @Query("UPDATE Pedido pedido SET pedido.situacao=false WHERE pedido.situacao = :id")
    public void statusEncerrado(@Param("id")Long id);

    @Query("SELECT pedido FROM Pedido pedido WHERE pedido.cadastro = :data")
    List<Pedido> totalPedidos(@Param("data") LocalDate date);

    @Query("SELECT pedido FROM Pedido pedido WHERE pedido.cadastro = :data AND pedido.situacao = false")
    List<Pedido> totalPedidosEncerrados(@Param("data") LocalDate date);

    @Query("SELECT pedido FROM Pedido pedido WHERE pedido.cadastro = :data AND pedido.entrega = true")
    List<Pedido> totalPedidosEntrega(@Param("data") LocalDate date);

    @Query("SELECT pedido FROM Pedido pedido WHERE pedido.cadastro = :data AND pedido.entrega = false")
    List<Pedido> totalPedidosRetira(@Param("data") LocalDate date);

    @Query("SELECT pedido FROM Pedido pedido WHERE pedido.cadastro = :data AND pedido.ativo = false")
    List<Pedido> totalPedidosCancelados(@Param("data") LocalDate date);

    @Query("SELECT sum(pedido.valor) FROM Pedido pedido WHERE pedido.cadastro = :data AND pedido.formaDePagamento = 'CARTAO'")
    BigDecimal valorTotalVendasCartao(@Param("data") LocalDate date);

    @Query("SELECT sum(pedido.valor) FROM Pedido pedido WHERE pedido.cadastro = :data AND pedido.formaDePagamento = 'DINHEIRO'")
    BigDecimal valorTotalVendasDinheiro(@Param("data") LocalDate date);


}
