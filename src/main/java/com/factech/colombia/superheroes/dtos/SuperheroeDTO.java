package com.factech.colombia.superheroes.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
public class SuperheroeDTO {

    @JsonProperty(value = "code")
    @NotNull(message = "code is required")
    private Integer codigo;

    @NotNull(message = "name is required")
    @Size(min = 1, max = 100,message = "name must be between 1 and 100 characters long")
    @JsonProperty(value = "name")
    private String nombre;

    @NotNull(message = "status is required")
    @JsonProperty(value = "status")
    private Boolean estado;

}
