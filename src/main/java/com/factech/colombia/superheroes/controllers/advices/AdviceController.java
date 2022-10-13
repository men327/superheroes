package com.factech.colombia.superheroes.controllers.advices;

import com.factech.colombia.superheroes.exceptions.SuperheroeException;
import com.factech.colombia.superheroes.dtos.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {

    private static final String COMMA = ";";

    @ExceptionHandler(value = SuperheroeException.class)
    public ResponseEntity<ErrorResponse> superheroeHandler(SuperheroeException exception) {
        return ResponseEntity.status(exception.getEstado()).body(ErrorResponse.builder().descripcion(exception.getDescripcion()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotValidException(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getAllErrors().
                stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(COMMA));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().descripcion(mensaje).build());
    }

}
