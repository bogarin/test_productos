package com.bogarin.productos.producto.excepciones.controlles;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.bogarin.productos.producto.excepciones.dtos.MessageDto;
import com.bogarin.productos.producto.excepciones.enums.TypeMessage;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private String keyMessage = "message";
    private String valueMessage = "no cumplen con la validación.";
    private String errorMessage = "Error capturado, pero no se pudo determinar la ubicación exacta.";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        log.error(setMessage(ex.getStackTrace(), ex.getMessage(), TypeMessage.ERROR));
        response.put(keyMessage, valueMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handlerMethodValidationException(HandlerMethodValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        log.error(setMessage(ex.getStackTrace(), ex.getMessage(), TypeMessage.ERROR));
        response.put(keyMessage, valueMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        log.warn(setMessage(ex.getStackTrace(), ex.getMessage(), TypeMessage.WARNING));
        response.put(keyMessage, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<Map<String, Object>> handleCannotCreateTransactionException(
            CannotCreateTransactionException ex) {
        Map<String, Object> response = new HashMap<>();
        log.error(setMessage(ex.getStackTrace(), ex.getMessage(), TypeMessage.ERROR));
        response.put(keyMessage, "Problemas de comunicación");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String setMessage(StackTraceElement[] stackTrace, String message, TypeMessage typeMessage) {
        if (stackTrace.length > 0) {
            StackTraceElement firstElement = stackTrace[0];
            MessageDto messageDto = MessageDto.builder()
                    .className(firstElement.getClassName().split("\\."))
                    .methodName(firstElement.getMethodName())
                    .lineNumber(firstElement.getLineNumber())
                    .typeMessage(typeMessage).message(message).build();
            return messageDto.toString();
        }
        return errorMessage;
    }
}
