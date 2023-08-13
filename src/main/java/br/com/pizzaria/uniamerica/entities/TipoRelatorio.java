package br.com.pizzaria.uniamerica.entities;

public enum TipoRelatorio {
    PEDIDOSDIARIOS("Pedidos diarios"),
    PEDIDOSENCERRADOS("Pedidos encerrados"),
    PEDIDOSENTREGUES("Pedidos entregues"),
    TOTALFATURAMENTO("Total faturamento");

    TipoRelatorio(String relatorio) {
    }
}
