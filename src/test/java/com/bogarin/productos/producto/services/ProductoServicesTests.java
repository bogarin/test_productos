package com.bogarin.productos.producto.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bogarin.productos.producto.dtos.ProductoDTO;
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
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        ProductoDTO result = services.productFindById(1L);
        assertNotNull(result);
        assertEquals(entity.getId(), result.getId().longValue());
        assertEquals(entity.getNombre(), result.getNombre());
        assertEquals(entity.getDescripcion(), result.getDescripcion());
    }

    @Test
    @DisplayName("Productos no encontrados")
    void casoDePruebaServices2() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> services.productFindById(1L));
    }

    @Test
    @DisplayName("Productos encontrado")
    void casoDePruebaServices3() {
        List<ProductoEntity> valueList = new ArrayList<>();
        valueList.add(new ProductoEntity(1L, "un producto", "el producto"));
        valueList.add(new ProductoEntity(2L, "otro producto", "el producto"));
        Iterable<ProductoEntity> iterable = valueList;
        when(repository.findAll()).thenReturn(iterable);
        List<ProductoDTO> resultSet = services.productoAll();
        assertFalse(resultSet.isEmpty());
        resultSet.forEach(producto -> {
            assertNotNull(producto);
            assertTrue(producto.getId() > 0);
            assertNotNull(producto.getNombre());
            assertNotNull(producto.getDescripcion());
        });
        for (int index = 0; index < valueList.size(); index++) {
            assertEquals(valueList.get(index).getId(), resultSet.get(index).getId().longValue());
            assertEquals(valueList.get(index).getNombre(), resultSet.get(index).getNombre());
            assertEquals(valueList.get(index).getDescripcion(), resultSet.get(index).getDescripcion());
        }

    }

    @Test
    @DisplayName("Producto no encontrado")
    void casoDePruebaServices4() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        List<ProductoDTO> resultSet = services.productoAll();
        assertTrue(resultSet.isEmpty());
    }
}
