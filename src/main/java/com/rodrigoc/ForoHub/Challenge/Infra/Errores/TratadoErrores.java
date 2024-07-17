package com.rodrigoc.ForoHub.Challenge.Infra.Errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class TratadoErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(datosError::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record datosError(String campo, String error){
        public datosError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }
}
