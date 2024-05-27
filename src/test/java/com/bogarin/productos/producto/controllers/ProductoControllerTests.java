package com.bogarin.productos.producto.controllers;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.bogarin.productos.producto.dtos.ProductoDTO;
import com.bogarin.productos.producto.services.ProductoServices;

@DisplayName("pruebas de  Controllers usando Mocks")
@ExtendWith(MockitoExtension.class)
class ProductoControllerTests {
    
	@InjectMocks
	ProductosController controller;

	@Mock
	ProductoServices services;


	@Test
	@Tag("unit")
	@DisplayName("Buscando Happy Path para obtener creando mocks de resultado")
	void casoDePruebaController1() throws Exception {
		ProductoDTO entity = new ProductoDTO(1l, "computadora", "compu");
		when(services.productFindById(1l)).thenReturn(entity);
		ResponseEntity<ProductoDTO> resultSet = controller.getProducto(1L);
		assertTrue(resultSet.getStatusCode().is2xxSuccessful());
		assertEquals(entity.getNombre(), resultSet.getBody().getNombre());
		assertEquals(entity.getDescripcion(), resultSet.getBody().getDescripcion());
		assertEquals(1L, resultSet.getBody().getId());
	}


}
