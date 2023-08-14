package br.com.pizzaria.uniamerica.entities;


/**
 * Author : Cristovao Martins
 */

public enum Cargo {
    CLIENTE("CLIENTE"),
    FUNCIONARIO("FUNCIONARIO");

    private final String value;

    Cargo(String value) {
        this.value = value;
    }
}
