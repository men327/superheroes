package com.factech.colombia.superheroes.controllers;


import com.factech.colombia.superheroes.dtos.SuperheroeDTO;
import com.factech.colombia.superheroes.entities.SuperheroeEntity;
import com.factech.colombia.superheroes.services.SuperheroeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SuperheroeControllerTest {

    private SuperheroeController superheroeController;

    @Mock
    private SuperheroeService superheroeService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        superheroeController = new SuperheroeController(superheroeService);
    }

    @Test
    void dadaUnaPeticionParaListarEntoncesEsExitosa() {
        Optional<String> parametro = Optional.empty();
        SuperheroeDTO superheroeDTO1 = SuperheroeDTO.builder().nombre("superman").codigo(1).estado(Boolean.TRUE).build();
        SuperheroeDTO superheroeDTO2 = SuperheroeDTO.builder().nombre("spiderman").codigo(2).estado(Boolean.FALSE).build();
        List<SuperheroeDTO> expectativa = Arrays.asList(superheroeDTO1, superheroeDTO2);

        Mockito.when(superheroeService.listar(Mockito.any())).thenReturn(expectativa);

        List<SuperheroeDTO> resultado = superheroeController.listar(parametro);

        Assertions.assertEquals(expectativa, resultado);
    }

    @Test
    void dadaUnaPeticionParaConsultarEntoncesEsExitosa() {
        Integer parametro = 1;
        SuperheroeDTO superheroeDTO = SuperheroeDTO.builder().nombre("superman").codigo(1).estado(Boolean.TRUE).build();

        Mockito.when(superheroeService.buscarPorCodigo(Mockito.anyInt())).thenReturn(superheroeDTO);

        SuperheroeDTO resultado = superheroeController.buscarPorCodigo(parametro);

        Assertions.assertEquals(superheroeDTO, resultado);
    }

    @Test
    void dadaUnaPeticionParaEditarEntoncesEsExitosa() {
        SuperheroeDTO superheroeDTO = SuperheroeDTO.builder().nombre("superman").codigo(1).estado(Boolean.TRUE).build();

        Mockito.when(superheroeService.editar(Mockito.any())).thenReturn(superheroeDTO);

        SuperheroeDTO resultado = superheroeController.editar(superheroeDTO);

        Assertions.assertEquals(superheroeDTO, resultado);
    }

    @Test
    void dadaUnaPeticionParaEliminarEntoncesEsExitosa() {
        Integer codigo = 1;

        Mockito.doNothing().when(superheroeService).eliminar(Mockito.anyInt());

        superheroeController.eliminar(codigo);

        Mockito.verify(superheroeService, Mockito.times(1)).eliminar(codigo);
    }

}
