package br.com.pizzaria.uniamerica.entities;

public enum FormaDePagamento {
    DINHEIRO ("DINHEIRO"),
    CARTÃO("CARTAO");

    private final String value;

    FormaDePagamento(String value) {
        this.value = value;
    }
}
