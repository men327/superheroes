package com.factech.colombia.superheroes.controllers;

import com.factech.colombia.superheroes.dtos.SuperheroeDTO;
import com.factech.colombia.superheroes.services.SuperheroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/superheroe")
public class SuperheroeController {

    private final SuperheroeService superheroeService;

    @Autowired
    public SuperheroeController(SuperheroeService superheroeService) {
        this.superheroeService = superheroeService;
    }

    @GetMapping
    public List<SuperheroeDTO> listar(@RequestParam(required = false) final Optional<String> nombre) {
        return this.superheroeService.listar(nombre);
    }

    @GetMapping("/{codigo}")
    public SuperheroeDTO buscarPorCodigo(@PathVariable("codigo") final Integer codigo) {
        return this.superheroeService.buscarPorCodigo(codigo);
    }

    @PutMapping
    public SuperheroeDTO editar(final SuperheroeDTO heroe) {
        return this.superheroeService.editar(heroe);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@PathVariable("codigo") final Integer codigo) {
        this.superheroeService.eliminar(codigo);
        return ResponseEntity.noContent().build();
    }

}
