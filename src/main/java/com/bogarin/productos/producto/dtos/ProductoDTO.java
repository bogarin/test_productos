package com.bogarin.productos.producto.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}
