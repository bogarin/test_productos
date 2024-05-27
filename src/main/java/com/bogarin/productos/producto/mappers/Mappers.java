package com.bogarin.productos.producto.mappers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import com.bogarin.productos.producto.dtos.ProductoDTO;
import com.bogarin.productos.producto.dtos.ProductoParamsDTO;
import com.bogarin.productos.producto.entitys.ProductoEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappers {

    public static final Function<ProductoEntity, ProductoDTO> entityToDto = entity -> ProductoDTO.builder()
            .id(entity.getId())
            .nombre(entity.getNombre())
            .descripcion(entity.getDescripcion())
            .build();

    public static final Function<ProductoDTO, ProductoEntity> DtoToEntity = dto -> ProductoEntity.builder()
            .id(dto.getId())
            .nombre(dto.getNombre())
            .descripcion(dto.getDescripcion())
            .build();

    public static final Function<ProductoParamsDTO, ProductoEntity> paramsDtoToEntity = paramsDto -> ProductoEntity
            .builder()
            .nombre(paramsDto.getNombre())
            .descripcion(paramsDto.getDescripcion())
            .build();

    public static final Function<Iterable<ProductoEntity>, List<ProductoDTO>> iterableEntityToListDto = entities -> {
        Iterator<ProductoEntity> iterator = entities.iterator();
        List<ProductoDTO> listDto = new ArrayList<>();
        while (iterator.hasNext()) {
            listDto.add(Mappers.entityToDto.apply(iterator.next()));
        }
        return listDto;
    };
}
