package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.entities.Pedido;
import br.com.pizzaria.uniamerica.repository.GerenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@Service
public class GerenciaService {
    @Autowired
    private GerenciaRepository gerenciaRepository;

    public List<Pedido> pedidosDoDia(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidos(date);
        escreveTxt("relatorio-dia.txt", pedidoList.toString());
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

    private void escreveTxt(String caminho, String conteudo) {
        try (
                FileWriter criaArquivo = new FileWriter(caminho, false); //false para apagar o conteúdo anterior e escrever de novo
                BufferedWriter buffer = new BufferedWriter(criaArquivo);
                PrintWriter escreveNoArquivo = new PrintWriter(buffer);
        ){
            escreveNoArquivo.append(conteudo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
