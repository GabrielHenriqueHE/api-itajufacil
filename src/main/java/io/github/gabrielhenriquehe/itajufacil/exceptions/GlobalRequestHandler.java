package io.github.gabrielhenriquehe.itajufacil.exceptions;

import io.github.gabrielhenriquehe.itajufacil.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalRequestHandler {

    @ExceptionHandler(InfoAlreadyInUseException.class)
    public ResponseEntity<ApiResponse<Void>> handleInfoAlreadyInUseException(InfoAlreadyInUseException e) {
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(InvalidDataProvidedException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidDataProvidedException(InvalidDataProvidedException e) {
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
