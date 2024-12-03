package io.github.gabrielhenriquehe.itajufacil.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Email já utilizado por outro usuário.")
public class InfoAlreadyInUseException extends RuntimeException {
    public InfoAlreadyInUseException(String message) {
        super(message);
    }
}
