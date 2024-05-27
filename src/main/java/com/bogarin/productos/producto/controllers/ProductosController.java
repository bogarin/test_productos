package com.bogarin.productos.producto.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bogarin.productos.producto.dtos.ProductoDTO;
import com.bogarin.productos.producto.dtos.ProductoParamsDTO;
import com.bogarin.productos.producto.entitys.ProductoEntity;
import com.bogarin.productos.producto.services.ProductoServices;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/productos")
@Log4j2
@AllArgsConstructor
public class ProductosController {

    ProductoServices services;
    private static final String VALID_MESSAGE_PARAM = "El ID del producto debe ser un número positivo";

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoDTO> getProducto(
            @Valid @Positive(message = VALID_MESSAGE_PARAM) @PathVariable Long id) {
        log.info("llego aquí ejecución");
        return ResponseEntity.ok(services.productFindById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        List<ProductoDTO> resultSetList = services.productoAll();
        return ResponseEntity.ok(resultSetList);
    }

    @PostMapping
    public ResponseEntity<ProductoEntity> createProducto(@Valid @RequestBody ProductoParamsDTO producto) {
        log.info(producto);
        return ResponseEntity.ok(new ProductoEntity());
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoDTO> updateProducto(
            @Valid @Positive(message = VALID_MESSAGE_PARAM) @PathVariable Long id,
            @RequestBody ProductoEntity productoDetails) {
        return ResponseEntity.ok(new ProductoDTO());
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProducto(@Valid @Positive(message = VALID_MESSAGE_PARAM) @PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

}
