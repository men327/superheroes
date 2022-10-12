package com.factech.colombia.superheroes.services;

import com.factech.colombia.superheroes.dtos.SuperheroeDTO;

import java.util.List;
import java.util.Optional;

public interface SuperheroeService {

    List<SuperheroeDTO> listar(final Optional<String> nombre);
    SuperheroeDTO buscarPorCodigo(final Integer codigo);
    SuperheroeDTO editar(final SuperheroeDTO heroe);
    void eliminar(final Integer codigo);

}
