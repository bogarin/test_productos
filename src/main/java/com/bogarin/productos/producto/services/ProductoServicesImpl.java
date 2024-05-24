package com.bogarin.productos.producto.services;

import org.springframework.stereotype.Service;

import com.bogarin.productos.producto.entitys.ProductoEntity;
import com.bogarin.productos.producto.repositorys.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoServicesImpl implements ProductoServices {

    ProductoRepository repository;

    public ProductoEntity productFindById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto con ID " + id + " no encontrado"));

    }
}
