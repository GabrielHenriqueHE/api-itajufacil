package io.github.gabrielhenriquehe.itajufacil.controllers;

import io.github.gabrielhenriquehe.itajufacil.dto.ApiResponse;
import io.github.gabrielhenriquehe.itajufacil.domain.user.UserRegisterDTO;
import io.github.gabrielhenriquehe.itajufacil.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody UserRegisterDTO data) {
        var user = this.authService.register(data);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Usuário registrado com sucesso.");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
