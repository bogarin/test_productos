package com.bogarin.productos.producto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.bogarin.productos.producto.controllers.ProductosController;
import com.bogarin.productos.producto.entitys.ProductoEntity;
import com.bogarin.productos.producto.services.ProductoServices;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class ProductoApplicationTests {

	@InjectMocks
	ProductosController controller;

	@Mock
	ProductoServices services;

	@Test
	void testProducto() throws Exception {
		ProductoEntity entity = new ProductoEntity(1l, "computadora", "compu");
		when(services.productFindById(1L)).thenReturn(entity);
		ResponseEntity<ProductoEntity> resultSet = controller.getProducto(1L);
		assertTrue(resultSet.getStatusCode().is2xxSuccessful());
		assertEquals(entity.getNombre(), resultSet.getBody().getNombre());
		assertEquals(entity.getDescripcion(), resultSet.getBody().getDescripcion());
	}

	@Test
	void testProducto2() throws Exception {
		when(services.productFindById(anyLong()))
				.thenThrow(new EntityNotFoundException());
		ResponseEntity<ProductoEntity> resultSet = controller.getProducto(1L);
		assertTrue(resultSet.getStatusCode().is4xxClientError());
	}

}
