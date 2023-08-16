package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.entities.Pedido;
import br.com.pizzaria.uniamerica.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GerenciaService {
    @Autowired
    private GerenciaService gerenciaService;

    public List<Pedido> pedidosDoDia(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaService.pedidosDoDia(date);
        return pedidoList;
    }

    public List<Pedido> pedidosEncerrados(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaService.pedidosEncerrados(date);
        return pedidoList;
    }

    public List<Pedido> pedidosEntrega(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaService.pedidosEntrega(date);
        return pedidoList;
    }

    public List<Pedido> pedidosRetira(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaService.pedidosRetira(date);
        return pedidoList;
    }

    public Long totalPedidosDinheiro(LocalDate date){
        Long total = this.gerenciaService.totalPedidosDinheiro(date);
        return total;
    }

    public Long totalPedidosCartao(LocalDate date){
        Long total = this.gerenciaService.totalPedidosCartao(date);
        return total;
    }
}
