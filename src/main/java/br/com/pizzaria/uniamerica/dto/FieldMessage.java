package br.com.pizzaria.uniamerica.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FieldMessage {

    @Getter
    private String fieldName;
    @Getter
    private String message;

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
