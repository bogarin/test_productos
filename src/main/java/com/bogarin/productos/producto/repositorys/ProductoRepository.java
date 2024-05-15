package com.bogarin.productos.producto.repositorys;

import org.springframework.stereotype.Repository;

import com.bogarin.productos.producto.entitys.ProductoEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoEntity, Long> {

}
