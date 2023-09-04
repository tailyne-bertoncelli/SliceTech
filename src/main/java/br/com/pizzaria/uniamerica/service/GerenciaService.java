package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.entities.Pedido;
import br.com.pizzaria.uniamerica.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class GerenciaService {
    @Autowired
    private PedidosRepository pedidosRepository;

    public List<Pedido> pedidosDoDia(LocalDate date){
        List<Pedido> pedidoList = this.pedidosRepository.totalPedidos(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-dia-"+ date +".txt", pedidoList);
        return pedidoList;
    }
    public List<Pedido> pedidosEncerrados(LocalDate date){
        List<Pedido> pedidoList = this.pedidosRepository.totalPedidosEncerrados(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-encerrados-"+ date +".txt", pedidoList);
        return pedidoList;
    }

    public List<Pedido> pedidosEntrega(LocalDate date){
        List<Pedido> pedidoList = this.pedidosRepository.totalPedidosEntrega(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-entrega-"+ date +".txt",pedidoList);
        return pedidoList;
    }

    public List<Pedido> pedidosRetira(LocalDate date){
        List<Pedido> pedidoList = this.pedidosRepository.totalPedidosRetira(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-retira-"+ date +".txt", pedidoList);
        return pedidoList;
    }

    public List<Pedido> pedidosCancelados(LocalDate date){
        List<Pedido> pedidoList = this.pedidosRepository.totalPedidosCancelados(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-cancelados-"+ date +".txt", pedidoList);
        return pedidoList;
    }

    public void valorTotalPedidos(LocalDate date){
        BigDecimal dinheiro = this.pedidosRepository.valorTotalVendasDinheiro(date);
        BigDecimal cartao = this.pedidosRepository.valorTotalVendasCartao(date);
        relatorioTotais("C:\\Users\\pc\\OneDrive\\Documentos\\valor-total"+ date +".txt", dinheiro, cartao);
    }


    private void escreveTxt(String caminho, List<Pedido> list) {
        try (
                FileWriter criaArquivo = new FileWriter(caminho, false); //false para apagar o conteúdo anterior e escrever de novo
                BufferedWriter buffer = new BufferedWriter(criaArquivo);
                PrintWriter escreveNoArquivo = new PrintWriter(buffer);
        ){
            for (int i = 0; i < list.size(); i++) {
                String situacao = list.get(i).isSituacao() ? "Em andamento" : "Encerrado";
                String cancelado = list.get(i).isAtivo() ? "Efetuado" : "Cancelado";
                String entrega = list.get(i).isEntrega() ? "Sim" : "Não";

                String comprovante = "---------- PEDIDO "+ i + " ----------" + "\n" +
                                    "Status: " + cancelado + "\n" +
                                    "Situação: " + situacao + "\n" +
                                    "Data: " + list.get(i).getCadastro().toString() + "\n" +
                                    "Pizza: " + list.get(i).getPizza().getDescricao().toString() + "\n" +
                                    "Tamanho: "+ list.get(i).getPizza().getTamanhoPizza().toString() + "\n" +
                                    "Observação: "+ list.get(i).getDescricao().toString() + "\n" +
                                    "\n" +
                                    "Pedido para entrega? " + entrega + "\n" +
                                    "Cliente: "+ list.get(i).getCliente().getNome().toString()+ "\n" +
                                    "Endereço: " + list.get(i).getCliente().getEndereco().getLogradouro().toString() + ", " + list.get(i).getCliente().getEndereco().getNumero().toString() + "\n" +
                                    "\n" +
                                    "Forma de pagamento: " + list.get(i).getFormaDePagamento().toString() + "\n" +
                                    "Valor total: R$ " + list.get(i).getValor();

                escreveNoArquivo.append(comprovante);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void relatorioTotais(String caminho, BigDecimal dinheiro, BigDecimal cartao) {
        try (
                FileWriter criaArquivo = new FileWriter(caminho, false); //false para apagar o conteúdo anterior e escrever de novo
                BufferedWriter buffer = new BufferedWriter(criaArquivo);
                PrintWriter escreveNoArquivo = new PrintWriter(buffer);
        ){
            escreveNoArquivo.append("Valor total de vendas no dinheiro R$" + dinheiro + "\n");
            escreveNoArquivo.append("Valor total de vendas no cartão R$" + cartao);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
