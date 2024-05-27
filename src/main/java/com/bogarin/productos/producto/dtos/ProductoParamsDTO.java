package com.bogarin.productos.producto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoParamsDTO {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Pattern(regexp = "^[A-Za-z0-9 ]{3,50}$", message = "El nombre del producto debe tener entre 3 y 50 caracteres y solo puede contener letras, números y espacios.")
    private String nombre;

    @NotBlank(message = "La descripción del producto es obligatoria")
    @Pattern(regexp = "^[A-Za-z0-9 ]{5,200}$", message = "La descripción del producto debe tener entre 5 y 200 caracteres y solo puede contener letras, números y espacios.")
    private String descripcion;
}
