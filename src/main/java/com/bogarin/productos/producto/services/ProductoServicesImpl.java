package com.bogarin.productos.producto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bogarin.productos.producto.dtos.ProductoDTO;
import com.bogarin.productos.producto.entitys.ProductoEntity;
import com.bogarin.productos.producto.mappers.Mappers;
import com.bogarin.productos.producto.repositorys.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoServicesImpl implements ProductoServices {

    private ProductoRepository repository;

    public ProductoEntity productFindById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto con ID " + id + " no encontrado"));

    }

    public List<ProductoDTO> productoAll() {
        Iterable<ProductoEntity> list = repository.findAll();
        return Mappers.iterableEntityToListDto.apply(list);
    }

}
