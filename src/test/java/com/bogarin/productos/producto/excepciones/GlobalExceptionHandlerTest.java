package com.bogarin.productos.producto.excepciones;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.bogarin.productos.producto.controllers.ProductosController;
import com.bogarin.productos.producto.dtos.ProductoDTO;
import com.bogarin.productos.producto.services.ProductoServices;

import jakarta.persistence.EntityNotFoundException;

@WebMvcTest(controllers = { ProductosController.class })
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoServices productoServices;

    @Test
    void testValidationException() throws Exception {

        when(productoServices.productFindById(anyLong()))
                .thenThrow(new EntityNotFoundException("Producto con ID 1 no encontrado"));
        mockMvc.perform(get("/productos/1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Producto con ID 1 no encontrado"));
    }

    @Test
    void testValidationException2() throws Exception {

        when(productoServices.productFindById(anyLong()))
                .thenThrow(new EntityNotFoundException("Producto con ID 1 no encontrado"));
        ResultActions resultActions = mockMvc.perform(get("/productos/1"));
        MvcResult mvcResult = resultActions.andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("contenido de response: " + content);

        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Producto con ID 1 no encontrado"));
    }

    @Test
    void testValidationException3() throws Exception {

        when(productoServices.productoAll()).thenReturn(new ArrayList<>());
        ResultActions resultActions = mockMvc.perform(get("/productos"));
        MvcResult mvcResult = resultActions.andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("contenido de response: " + content);

        resultActions.andExpect(status().isOk());
        assertEquals("[]", content);
    }

    @Test
    void testValidationException4() throws Exception {

        List<ProductoDTO> valueList = new ArrayList<>();
        valueList.add(new ProductoDTO(1L, "un producto", "el producto"));
        valueList.add(new ProductoDTO(2L, "otro producto", "el producto"));
        when(productoServices.productoAll()).thenReturn(valueList);
        ResultActions resultActions = mockMvc.perform(get("/productos"));
        MvcResult mvcResult = resultActions.andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("contenido de response: " + content);

        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$", hasSize(valueList.size())));
        for (int index = 0; index < valueList.size(); index++) {
            resultActions.andExpect(jsonPath("$[" + index + "].id", is(valueList.get(index).getId().intValue())))
                    .andExpect(jsonPath("$[" + index + "].nombre", is(valueList.get(index).getNombre())))
                    .andExpect(jsonPath("$[" + index + "].descripcion", is(valueList.get(index).getDescripcion())));
        }
    }
}
