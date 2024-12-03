package io.github.gabrielhenriquehe.itajufacil.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Dados inválidos.")
public class InvalidDataProvidedException extends RuntimeException {
    public InvalidDataProvidedException(String message) {
        super(message);
    }
}
