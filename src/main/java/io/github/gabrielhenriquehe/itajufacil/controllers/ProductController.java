package io.github.gabrielhenriquehe.itajufacil.controllers;

import io.github.gabrielhenriquehe.itajufacil.domain.product.Product;
import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductRegisterDTO;
import io.github.gabrielhenriquehe.itajufacil.dto.ApiResponse;
import io.github.gabrielhenriquehe.itajufacil.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<Void>> registerProduct(@RequestBody ProductRegisterDTO data) {
        this.service.registerProduct(data);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Produto criado com sucesso.");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {
        List<Product> products = this.service.findAll();
        ApiResponse<List<Product>> response = new ApiResponse<>(HttpStatus.OK.value(), null, products);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
