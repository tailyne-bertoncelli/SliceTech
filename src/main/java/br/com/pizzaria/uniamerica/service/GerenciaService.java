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
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-dia-"+ date +".txt", pedidoList);
        return pedidoList;
    }
    public List<Pedido> pedidosEncerrados(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidosEncerrados(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-encerrados-"+ date +".txt", pedidoList);
        return pedidoList;
    }

    public List<Pedido> pedidosEntrega(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidosEntrega(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-entrega-"+ date +".txt",pedidoList);
        return pedidoList;
    }

    public List<Pedido> pedidosRetira(LocalDate date){
        List<Pedido> pedidoList = this.gerenciaRepository.totalPedidosRetira(date);
        escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-retira-"+ date +".txt", pedidoList);
        return pedidoList;
    }

    public double totalPedidosDinheiro(LocalDate date){
        double total = this.gerenciaRepository.valorTotalVendasDinheiro(date);
        //escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-pago-dinheiro-"+ date +".txt", String.valueOf(total));
        return total;
    }

    public double totalPedidosCartao(LocalDate date){
        double total = this.gerenciaRepository.valorTotalVendasCartao(date);
        //escreveTxt("C:\\Users\\pc\\OneDrive\\Documentos\\pedidos-pago-cartao-"+ date +".txt", String.valueOf(total));
        return total;
    }

    private void escreveTxt(String caminho, List<Pedido> list) {

        
        try (
                FileWriter criaArquivo = new FileWriter(caminho, false); //false para apagar o conteúdo anterior e escrever de novo
                BufferedWriter buffer = new BufferedWriter(criaArquivo);
                PrintWriter escreveNoArquivo = new PrintWriter(buffer);
        ){

            for (int i = 0; i < list.size(); i++) {
                String comprovante = "---------- PEDIDO "+ i + " ----------" + "\n" +
                                "Status: " + list.get(i).isSituacao() + "\n" +
                                "Data: " + list.get(i).getCadastro().toString() + "\n" +
                                "Pizza: " + list.get(i).getPizza().getDescricao().toString() + "\n" +
                                "Tamanho: "+ list.get(i).getPizza().getTamanhoPizza().toString() + "\n" +
                                "Observação: "+ list.get(i).getDescricao().toString() + "\n" +
                                "\n" +
                                "Pedido para entrega? " + list.get(i).isEntrega() + "\n" +
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

//    private void deixaListaBonitinha(List<Pedido> pedidoList) {
//        for (int i = 0; i < pedidoList.size(); i++) {
//            System.out.println("----- PEDIDO "+ i + "-----");
//            System.out.println("Pizza - "+ pedidoList.get(i).getPizza());
//            System.out.println("Sabor - "+ pedidoList.get(i).getSabor());
//            System.out.println("Descricao - "+ pedidoList.get(i).getDescricao());
//            System.out.println("Valor - " + pedidoList.get(i).getValor());
//            System.out.println("Entrega - "+ pedidoList.get(i).isEntrega());
//            System.out.println("Situação - "+ pedidoList.get(i).isSituacao());
//            System.out.println("Cliente - " + pedidoList.get(i).getCliente());
//            System.out.println("Produtos adicionais - "+ pedidoList.get(i).getProdutosList());
//        }
//    }
}
