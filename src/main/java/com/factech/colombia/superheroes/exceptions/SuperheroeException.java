package com.factech.colombia.superheroes.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuperheroeException extends RuntimeException {

    private final HttpStatus estado;
    private final String descripcion;

    public SuperheroeException(HttpStatus estado, String descripcion) {
        this.estado = estado;
        this.descripcion = descripcion;
    }

}
