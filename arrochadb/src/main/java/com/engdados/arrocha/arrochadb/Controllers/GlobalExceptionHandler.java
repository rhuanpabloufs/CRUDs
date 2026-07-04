package com.engdados.arrocha.arrochadb.Controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleDadosErrados(HttpMessageNotReadableException e){
        return ResponseEntity.status(400).body("Algum campo está inválido");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlerDadosTiposViolados(DataIntegrityViolationException e){
        return ResponseEntity.status(400).body("Algum campo está inválido");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException e){
        String mensagem = "";
        for(FieldError erro : e.getFieldErrors()){
            mensagem += erro.getField();
            mensagem += " = " + erro.getDefaultMessage() + " ,";
        }
        return ResponseEntity.status(400).body(mensagem);
    }
    
}
