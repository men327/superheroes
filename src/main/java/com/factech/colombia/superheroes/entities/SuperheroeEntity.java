package com.factech.colombia.superheroes.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "superheroes")
public class SuperheroeEntity {

    @Id
    @Column
    private Integer codigo;

    @Column
    private String nombre;

    @Column
    private Boolean activo;

}
