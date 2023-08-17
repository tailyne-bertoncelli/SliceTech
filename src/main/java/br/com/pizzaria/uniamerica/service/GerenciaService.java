package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.entities.Pedido;
import br.com.pizzaria.uniamerica.repository.GerenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GerenciaService {
    @Autowired
    private GerenciaRepository gerenciaRepository;

    public List<Pedido> pedidosDoDia(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidos(date);
        return pedidoList;
    }

    public List<Pedido> pedidosEncerrados(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidosEncerrados(date);
        return pedidoList;
    }

    public List<Pedido> pedidosEntrega(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidosEntrega(date);
        return pedidoList;
    }

    public List<Pedido> pedidosRetira(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidosRetira(date);
        return pedidoList;
    }

    public double totalPedidosDinheiro(LocalDate date){
        double total = this.gerenciaRepository.valorTotalVendasDinheiro(date);
        return total;
    }

    public double totalPedidosCartao(LocalDate date){
        double total = this.gerenciaRepository.valorTotalVendasCartao(date);
        return total;
    }
}
