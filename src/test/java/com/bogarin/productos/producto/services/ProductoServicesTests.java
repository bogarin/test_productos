package com.bogarin.productos.producto.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bogarin.productos.producto.entitys.ProductoEntity;
import com.bogarin.productos.producto.repositorys.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@DisplayName("Pruebas de services usando Mocks")
@ExtendWith(MockitoExtension.class)
class ProductoServicesTests {

    @InjectMocks
    private ProductoServicesImpl services;

    @Mock
    private ProductoRepository repository;

    @Test
    @DisplayName("Buscando Happy Path")
    void casoDePruebaServices1() {
        ProductoEntity entity = new ProductoEntity();
        entity.setId(1L);
        entity.setNombre("computadora");
        entity.setDescripcion("compu");
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        ProductoEntity result = services.productFindById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("computadora", result.getNombre());
        assertEquals("compu", result.getDescripcion());
    }

    @Test
    @DisplayName("Producto no encontrado")
    void casoDePruebaServices2() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> services.productFindById(1L));
    }
}
