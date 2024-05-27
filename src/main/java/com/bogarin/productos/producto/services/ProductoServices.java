package com.bogarin.productos.producto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bogarin.productos.producto.dtos.ProductoDTO;
import com.bogarin.productos.producto.entitys.ProductoEntity;

@Service
public interface ProductoServices {

    public ProductoEntity productFindById(Long id);

    public List<ProductoDTO> productoAll();
}