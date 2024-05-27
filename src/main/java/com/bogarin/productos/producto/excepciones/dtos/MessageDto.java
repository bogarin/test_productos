package com.bogarin.productos.producto.excepciones.dtos;

import com.bogarin.productos.producto.excepciones.enums.TypeMessage;

import lombok.Builder;

@Builder
public class MessageDto {
      
    private String[] className;
    private String methodName;
    private int lineNumber;
    private String message;
    private TypeMessage typeMessage;

    @Override
    public String toString(){
        return typeMessage.toString()+" capturado en la clase: " + className[className.length - 2] + "."
        + className[className.length - 1] + ", en la función: "
        + methodName + ", en la línea: "
        + lineNumber + " message: " + message;
    }
}
