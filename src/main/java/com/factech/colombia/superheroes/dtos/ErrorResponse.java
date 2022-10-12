package com.factech.colombia.superheroes.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class ErrorResponse {

    private String descripcion;

}
