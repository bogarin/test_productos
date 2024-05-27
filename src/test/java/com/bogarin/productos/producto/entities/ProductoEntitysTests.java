package com.bogarin.productos.producto.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bogarin.productos.producto.entitys.ProductoEntity;

@DisplayName("Pruebas de entities usando Mocks")
@ExtendWith(MockitoExtension.class)
class ProductoEntitysTests {

    @InjectMocks
    private ProductoEntity productoEntity;

    private long id = 1L;
    private String nombre = "Producto";
    private String descripcion = "Descripción del producto";

    @Test
    @DisplayName("Verificar valores de los atributos con builder")
    void testAtributosProductoEntity() {
        productoEntity = ProductoEntity.builder().id(id).nombre(nombre)
        .descripcion(descripcion).build();
        assertNotNull(productoEntity);
        assertEquals(id, productoEntity.getId());
        assertEquals(nombre, productoEntity.getNombre());
        assertEquals(descripcion, productoEntity.getDescripcion());
    }

    @Test
    @DisplayName("Verificar valores de los atributos")
    void testAtributosProductoEntity2() {
        productoEntity = new ProductoEntity();
        productoEntity.setId(id);
        productoEntity.setNombre(nombre);
        productoEntity.setDescripcion(descripcion);
        assertNotNull(productoEntity);
        assertEquals(id, productoEntity.getId());
        assertEquals(nombre, productoEntity.getNombre());
        assertEquals(descripcion, productoEntity.getDescripcion());
    }
    @Test
    @DisplayName("Verificar valores de los atributos")
    void testAtributosProductoEntity3() {
        assertEquals(productoEntity.getClass(),ProductoEntity.class);
    }
}
