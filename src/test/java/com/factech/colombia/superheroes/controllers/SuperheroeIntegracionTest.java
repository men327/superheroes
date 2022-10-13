package com.factech.colombia.superheroes.controllers;

import com.factech.colombia.superheroes.dtos.SuperheroeDTO;
import com.factech.colombia.superheroes.exceptions.SuperheroeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SuperheroeIntegracionTest {

    @Autowired
    private SuperheroeController superheroeController;

    @Test
    void dadaUnaPeticicionConsultaPorNombreEsExitoso() {
        List<SuperheroeDTO> expectativa = Arrays.asList(SuperheroeDTO.builder().codigo(8).nombre("mujer maravilla").estado(Boolean.TRUE).build());

        List<SuperheroeDTO> superheroeDTOS = superheroeController.listar(Optional.of("mujer"));

        Assertions.assertEquals(expectativa, superheroeDTOS);
    }

    @Test
    void dadaUnaPeticicionConsultaPorCodigoFalla() {
        Integer codigo = 100;

        Assertions.assertThrows(SuperheroeException.class, () -> superheroeController.buscarPorCodigo(codigo));
    }

    @Test
    void dadaUnaPeticicionParaEditarEsExitoso() {
        SuperheroeDTO superheroeDTO = SuperheroeDTO.builder().codigo(8).nombre("mujer x").estado(Boolean.FALSE).build();

        SuperheroeDTO resultado =superheroeController.editar(superheroeDTO);

        Assertions.assertEquals(superheroeDTO, resultado);
    }

}
