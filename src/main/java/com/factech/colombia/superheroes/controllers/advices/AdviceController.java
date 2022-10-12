package com.factech.colombia.superheroes.controllers.advices;

import com.factech.colombia.superheroes.exceptions.SuperheroeException;
import com.factech.colombia.superheroes.dtos.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = SuperheroeException.class)
    public ResponseEntity<ErrorResponse> superheroeHandler(SuperheroeException exception) {
        return ResponseEntity.status(exception.getEstado()).body(ErrorResponse.builder().descripcion(exception.getDescripcion()).build());
    }

}
