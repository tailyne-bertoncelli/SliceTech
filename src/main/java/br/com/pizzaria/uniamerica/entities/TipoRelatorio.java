package br.com.pizzaria.uniamerica.entities;

public enum TipoRelatorio {
    PEDIDOSDIARIOS("Pedidos diarios"),
    PEDIDOSENCERRADOS("Pedidos encerrados"),
    PEDIDOSENTREGA("Pedidos entrega"),
    PEDIDOSRETIRA("Pedidos retira"),
    TOTALCARTAO("Total cartão"),
    TOTALDINHEIRO("Total dinheiro");

    TipoRelatorio(String relatorio) {
    }
}
