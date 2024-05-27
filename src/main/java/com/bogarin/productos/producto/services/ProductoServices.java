package com.bogarin.productos.producto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bogarin.productos.producto.dtos.ProductoDTO;

@Service
public interface ProductoServices {

    public ProductoDTO productFindById(Long id);

    public List<ProductoDTO> productoAll();
}