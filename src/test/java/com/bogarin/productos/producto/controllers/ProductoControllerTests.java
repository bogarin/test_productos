package com.bogarin.productos.producto.controllers;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.bogarin.productos.producto.entitys.ProductoEntity;
import com.bogarin.productos.producto.services.ProductoServices;

import jakarta.persistence.EntityNotFoundException;

@DisplayName("pruebas de  Controllers usando Mocks")
@ExtendWith(MockitoExtension.class)
class ProductoControllerTests {
    
	@InjectMocks
	ProductosController controller;

	@Mock
	ProductoServices services;


	@Test
	@Tag("unitController")
	@DisplayName("Buscando Happy Path para obtener creando mocks de resultado")
	void casoDePruebaController1() throws Exception {
		ProductoEntity entity = new ProductoEntity(1l, "computadora", "compu");
		when(services.productFindById(1L)).thenReturn(entity);
		ResponseEntity<ProductoEntity> resultSet = controller.getProducto(1L);
		assertTrue(resultSet.getStatusCode().is2xxSuccessful());
		assertEquals(entity.getNombre(), resultSet.getBody().getNombre());
		assertEquals(entity.getDescripcion(), resultSet.getBody().getDescripcion());
		assertEquals(1L, resultSet.getBody().getId());
	}

	@Test
	@Tag("unitController")
	@DisplayName("Buscando Happy Path para obtener un resultado vacio con mocks")
	void casoDePruebaController2() throws Exception {
		when(services.productFindById(anyLong()))
				.thenThrow(new EntityNotFoundException());
		ResponseEntity<ProductoEntity> resultSet = controller.getProducto(1L);
		assertTrue(resultSet.getStatusCode().is4xxClientError());
	}
}
