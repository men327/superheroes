package com.factech.colombia.superheroes.services.impl;

import com.factech.colombia.superheroes.exceptions.SuperheroeException;
import com.factech.colombia.superheroes.dtos.SuperheroeDTO;
import com.factech.colombia.superheroes.entities.SuperheroeEntity;
import com.factech.colombia.superheroes.mappers.SuperheroeMapper;
import com.factech.colombia.superheroes.repositories.SuperheroeRepository;
import com.factech.colombia.superheroes.services.SuperheroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuperheroeServiceImpl implements SuperheroeService {

    private final SuperheroeRepository superheroeRepository;
    private final SuperheroeMapper superheroeMapper;

    @Autowired
    public SuperheroeServiceImpl(SuperheroeRepository superheroeRepository, SuperheroeMapper superheroeMapper) {
        this.superheroeRepository = superheroeRepository;
        this.superheroeMapper = superheroeMapper;
    }

    @Override
    public List<SuperheroeDTO> listar(final Optional<String> nombre) {
        return obtenerDatos(nombre)
                .stream().map(superheroeMapper::toDto).collect(Collectors.toList());
    }

    private List<SuperheroeEntity> obtenerDatos(final Optional<String> nombre) {
        return nombre.isPresent()?this.superheroeRepository.findByNombreContainingIgnoreCase(nombre.get()) : this.superheroeRepository.findAll();
    }

    @Override
    public SuperheroeDTO buscarPorCodigo(final Integer codigo) {
        return this.superheroeMapper.toDto(validarExiste(codigo));
    }

    @Override
    public SuperheroeDTO editar(final SuperheroeDTO heroeData) {
        SuperheroeEntity heroe = validarExiste(heroeData.getCodigo());
        heroe.setNombre(heroeData.getNombre());
        heroe.setActivo(heroeData.getEstado());
        this.superheroeRepository.save(heroe);
        return this.superheroeMapper.toDto(heroe);
    }

    @Override
    public void eliminar(Integer codigo) {
        this.superheroeRepository.delete(validarExiste(codigo));
    }

    private SuperheroeEntity validarExiste(final Integer codigo) {
        Optional<SuperheroeEntity> heroe = this.superheroeRepository.findById(codigo);
        if(!heroe.isPresent()) {
            throw new SuperheroeException(HttpStatus.NOT_FOUND, "Superheroe no encontrado");
        }
        return heroe.get();
    }


}
