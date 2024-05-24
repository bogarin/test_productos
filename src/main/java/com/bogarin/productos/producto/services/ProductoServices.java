package com.bogarin.productos.producto.services;

import org.springframework.stereotype.Service;

import com.bogarin.productos.producto.entitys.ProductoEntity;

@Service
public interface ProductoServices {


    public ProductoEntity productFindById(Long id);
}