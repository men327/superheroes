package com.factech.colombia.superheroes.services;

import com.factech.colombia.superheroes.dtos.SuperheroeDTO;
import com.factech.colombia.superheroes.entities.SuperheroeEntity;
import com.factech.colombia.superheroes.exceptions.SuperheroeException;
import com.factech.colombia.superheroes.mappers.SuperheroeMapper;
import com.factech.colombia.superheroes.repositories.SuperheroeRepository;
import com.factech.colombia.superheroes.services.impl.SuperheroeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class SuperheroeServiceImplTest {

    private SuperheroeServiceImpl superheroeService;

    @Mock
    private SuperheroeRepository superheroeRepository;

    @Mock
    private SuperheroeMapper superheroeMapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        superheroeService = new SuperheroeServiceImpl(superheroeRepository, superheroeMapper);
    }

    @Test
    void dadaUnaConsultaEntoncesObtieneDatos() {
        Optional<String> nombre = Optional.empty();
        SuperheroeEntity superheroe1 = SuperheroeEntity.builder().nombre("superman").codigo(1).activo(Boolean.TRUE).build();
        SuperheroeEntity superheroe2 = SuperheroeEntity.builder().nombre("spiderman").codigo(2).activo(Boolean.FALSE).build();
        SuperheroeDTO superheroeDTO1 = SuperheroeDTO.builder().nombre("superman").codigo(1).estado(Boolean.TRUE).build();
        SuperheroeDTO superheroeDTO2 = SuperheroeDTO.builder().nombre("spiderman").codigo(2).estado(Boolean.FALSE).build();
        List<SuperheroeEntity> heroes = Arrays.asList(superheroe1, superheroe2);
        List<SuperheroeDTO> expectativa = Arrays.asList(superheroeDTO1, superheroeDTO2);

        Mockito.when(superheroeRepository.findAll()).thenReturn(heroes);
        Mockito.when(superheroeMapper.toDto(superheroe1)).thenReturn(superheroeDTO1);
        Mockito.when(superheroeMapper.toDto(superheroe2)).thenReturn(superheroeDTO2);

        List<SuperheroeDTO> resultado = superheroeService.listar(nombre);

        Assertions.assertEquals(expectativa, resultado);
    }

    @Test
    void dadaUnaConsultaEntoncesPorNombreObtieneDatos() {
        Optional<String> nombre = Optional.of("man");
        SuperheroeEntity superheroe1 = SuperheroeEntity.builder().nombre("superman").codigo(1).activo(Boolean.TRUE).build();
        SuperheroeEntity superheroe2 = SuperheroeEntity.builder().nombre("spiderman").codigo(2).activo(Boolean.FALSE).build();
        SuperheroeDTO superheroeDTO1 = SuperheroeDTO.builder().nombre("superman").codigo(1).estado(Boolean.TRUE).build();
        SuperheroeDTO superheroeDTO2 = SuperheroeDTO.builder().nombre("spiderman").codigo(2).estado(Boolean.FALSE).build();
        List<SuperheroeEntity> heroes = Arrays.asList(superheroe1, superheroe2);
        List<SuperheroeDTO> expectativa = Arrays.asList(superheroeDTO1, superheroeDTO2);

        Mockito.when(superheroeRepository.findByNombreContainingIgnoreCase(Mockito.anyString())).thenReturn(heroes);
        Mockito.when(superheroeMapper.toDto(superheroe1)).thenReturn(superheroeDTO1);
        Mockito.when(superheroeMapper.toDto(superheroe2)).thenReturn(superheroeDTO2);

        List<SuperheroeDTO> resultado = superheroeService.listar(nombre);

        Assertions.assertEquals(expectativa, resultado);
    }

    @Test
    void dadaUnCodigoParaEliminarEntoncesNoExiste() {
        Integer codigo = 1;
        Optional<SuperheroeEntity> resultado = Optional.empty();

        Mockito.when(superheroeRepository.findById(Mockito.anyInt())).thenReturn(resultado);

        Assertions.assertThrows(SuperheroeException.class, () -> superheroeService.eliminar(codigo));
    }

    @Test
    void dadaUnCodigoParaEliminarEntoncesEsExitoso() {
        Integer codigo = 1;
        SuperheroeEntity superheroe1 = SuperheroeEntity.builder().nombre("superman").codigo(1).activo(Boolean.TRUE).build();
        Optional<SuperheroeEntity> resultado = Optional.of(superheroe1);

        Mockito.when(superheroeRepository.findById(Mockito.anyInt())).thenReturn(resultado);
        Mockito.doNothing().when(superheroeRepository).delete(superheroe1);

        superheroeService.eliminar(codigo);

        Mockito.verify(superheroeRepository, Mockito.times(1)).delete(superheroe1);
    }

}
