package com.factech.colombia.superheroes.controllers;

import com.factech.colombia.superheroes.customer.ContadorTiempoLog;
import com.factech.colombia.superheroes.dtos.SuperheroeDTO;
import com.factech.colombia.superheroes.services.SuperheroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/superheroe")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@CacheConfig(cacheNames={"superheroes"})
public class SuperheroeController {

    private final SuperheroeService superheroeService;

    @Autowired
    public SuperheroeController(SuperheroeService superheroeService) {
        this.superheroeService = superheroeService;
    }

    @GetMapping
    @Cacheable
    @ContadorTiempoLog
    public List<SuperheroeDTO> listar(@RequestParam(required = false) final Optional<String> nombre) {
        return this.superheroeService.listar(nombre);
    }

    @GetMapping("/{codigo}")
    @Cacheable
    @ContadorTiempoLog
    public SuperheroeDTO buscarPorCodigo(@PathVariable("codigo") final Integer codigo) {
        return this.superheroeService.buscarPorCodigo(codigo);
    }

    @PutMapping
    @CacheEvict(allEntries = true)
    @ContadorTiempoLog
    public SuperheroeDTO editar(@RequestBody final SuperheroeDTO heroe) {
        return this.superheroeService.editar(heroe);
    }

    @DeleteMapping("/{codigo}")
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ContadorTiempoLog
    public void eliminar(@PathVariable("codigo") final Integer codigo) {
        this.superheroeService.eliminar(codigo);
    }

}
