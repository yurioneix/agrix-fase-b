package com.betrybe.agrix.controller.advice;

import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe de controle de exceções.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

  /**
   * Método que lida com a exceção de Farm não encontrado.
   */
  @ExceptionHandler({FarmNotFoundException.class, CropNotFoundException.class})
  public ResponseEntity<String> handleNotFoundException(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }


  /**
   * Método que lida com exceções genéricas.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }
}
