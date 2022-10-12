package com.factech.colombia.superheroes.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class SuperheroeDTO {

    @JsonProperty(value = "code")
    private Integer codigo;

    @JsonProperty(value = "name")
    private String nombre;

    @JsonProperty(value = "status")
    private Boolean estado;

}
