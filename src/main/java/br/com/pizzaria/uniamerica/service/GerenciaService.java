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
        deixaListaBonitinha(pedidoList);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-dia-"+ date +".txt", pedidoList.toString());
        return pedidoList;
    }
    public List<Pedido> pedidosEncerrados(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidosEncerrados(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-encerrados-"+ date +".txt", pedidoList.toString());
        return pedidoList;
    }

    public List<Pedido> pedidosEntrega(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidosEntrega(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-entrega-"+ date +".txt", pedidoList.toString());
        return pedidoList;
    }

    public List<Pedido> pedidosRetira(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidosRetira(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-retira-"+ date +".txt", pedidoList.toString());
        return pedidoList;
    }

    public double totalPedidosDinheiro(LocalDate date){
        double total = this.gerenciaRepository.valorTotalVendasDinheiro(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-pago-dinheiro-"+ date +".txt", String.valueOf(total));
        return total;
    }

    public double totalPedidosCartao(LocalDate date){
        double total = this.gerenciaRepository.valorTotalVendasCartao(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-pago-cartao-"+ date +".txt", String.valueOf(total));
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

    private void deixaListaBonitinha(List<Pedido> pedidoList) {
        for (int i = 0; i < pedidoList.size(); i++) {
            System.out.println("----- PEDIDO "+ i + "-----");
            System.out.println("Pizza - "+ pedidoList.get(i).getPizza());
            System.out.println("Sabor - "+ pedidoList.get(i).getSabor());
            System.out.println("Descricao - "+ pedidoList.get(i).getDescricao());
            System.out.println("Valor - " + pedidoList.get(i).getValor());
            System.out.println("Entrega - "+ pedidoList.get(i).isEntrega());
            System.out.println("Situação - "+ pedidoList.get(i).isSituacao());
            System.out.println("Cliente - " + pedidoList.get(i).getCliente());
            System.out.println("Produtos adicionais - "+ pedidoList.get(i).getProdutosList());
        }
    }
}
