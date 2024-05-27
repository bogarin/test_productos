package com.bogarin.productos.producto.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.bogarin.productos.producto.entitys.ProductoEntity;
import com.bogarin.productos.producto.repositorys.ProductoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisplayName("pruebas de  Controllers usando Bases de datos")
@SpringBootTest // Carga el contexto completo de la aplicación
@AutoConfigureMockMvc // Autoconfigura MockMvc para pruebas de controladores
@Testcontainers // Habilita el soporte de Testcontainers para las pruebas
class ProductoApplicationTests {

	@Autowired
	private MockMvc mockMvc; // Inyección de MockMvc para realizar solicitudes HTTP en pruebas
	@Autowired
	private ProductoRepository repository;

	// Definición del contenedor de PostgreSQL para las pruebas
	@SuppressWarnings("resource")
	@Container
	private static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
			.withDatabaseName("testdb").withUsername("testuser").withPassword("testpass");

	// Configuración dinámica de propiedades para usar las credenciales del
	// contenedor
	@DynamicPropertySource
	static void configurePostgresProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresContainer::getUsername);
		registry.add("spring.datasource.password", postgresContainer::getPassword);
	}

	@Test
	@Tag("integrations")
	void hola() throws Exception {
		ProductoEntity producto = new ProductoEntity();
		producto.setNombre("Producto Existente");
		producto.setDescripcion("Descripcion del Producto Existente");
		producto = repository.save(producto);
		log.info(producto.toString());
		mockMvc.perform(get("/productos/" + producto.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nombre").value(producto.getNombre()))
				.andExpect(jsonPath("$.descripcion").value(producto.getDescripcion()));
	}

	@Test
	@Tag("integrations")
	void hola2() throws Exception {
		long nonExistingProductId = 999L;

		mockMvc.perform(get("/productos/" + nonExistingProductId))
				.andExpect(status().is4xxClientError());
	}

}
