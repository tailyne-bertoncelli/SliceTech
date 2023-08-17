package br.com.pizzaria.uniamerica.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
public class CustomError {
    @Getter
    private Instant timestamp;
    @Getter
    private Integer status;
    @Getter
    private String error;
    @Getter
    private String path;


    public CustomError(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
