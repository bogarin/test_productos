package com.bogarin.productos.producto.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bogarin.productos.producto.entitys.ProductoEntity;
import com.bogarin.productos.producto.services.ProductoServices;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/productos")
@Log4j2
@AllArgsConstructor
public class ProductosController {

    ProductoServices services;

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoEntity> getProducto(@PathVariable Long id) {
        try {
            log.info("llego aqui ejecucion");
            return ResponseEntity.ok(services.productFindById(id));
        } catch (Exception e) {
            log.info("llego aqui error");
            return ResponseEntity.notFound().build();
        }

    }

}
