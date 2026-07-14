package com.engdados.arrocha.arrochadb.Controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.engdados.arrocha.arrochadb.Exceptions.*;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleRuntime(NotFound e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(DadosDuplicado.class)
    public ResponseEntity<String> handleRuntime(DadosDuplicado e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleDadosErrados(HttpMessageNotReadableException e){
        return ResponseEntity.status(400).body("Algum campo está inválido no Json. Dica: Tipos Enums devem ser colocados com mesmo nome");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlerDadosTiposViolados(DataIntegrityViolationException e){
        return ResponseEntity.status(400).body("Algum campo violou integridade de dados no banco, observe se não está faltando campos ou tipo errado");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException e){
        String mensagem = "";
        for(FieldError erro : e.getFieldErrors()){
            mensagem += erro.getField();
            mensagem += " = " + erro.getDefaultMessage() + " || ";
        }
        return ResponseEntity.status(400).body(mensagem);
    }
}
