package com.devsuperior.bds02.controller.exceptions;

import com.devsuperior.bds02.services.handlers.DataBaseException;
import com.devsuperior.bds02.services.handlers.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<com.devsuperior.bds02.controller.exceptions.StandrError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        com.devsuperior.bds02.controller.exceptions.StandrError err = new com.devsuperior.bds02.controller.exceptions.StandrError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Não Encontrado");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<com.devsuperior.bds02.controller.exceptions.StandrError> dataBase(DataBaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        com.devsuperior.bds02.controller.exceptions.StandrError err = new com.devsuperior.bds02.controller.exceptions.StandrError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Database Exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
