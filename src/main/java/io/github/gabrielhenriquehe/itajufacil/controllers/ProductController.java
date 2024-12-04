package io.github.gabrielhenriquehe.itajufacil.controllers;

import io.github.gabrielhenriquehe.itajufacil.domain.product.Product;
import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductPatchDTO;
import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductRegisterDTO;
import io.github.gabrielhenriquehe.itajufacil.dto.ApiResponse;
import io.github.gabrielhenriquehe.itajufacil.dto.ProductResponseDTO;
import io.github.gabrielhenriquehe.itajufacil.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<Void>> registerProduct(@RequestBody ProductRegisterDTO data, @RequestHeader("user-id") String id) {
        UUID userId = UUID.fromString(id);
        this.service.registerProduct(data, userId);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Produto criado com sucesso.");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<ApiResponse<Void>> patchProduct(@PathVariable UUID id, @RequestBody ProductPatchDTO data, @RequestHeader("user-id") String userId) {
        this.service.patchProduct(id, UUID.fromString(userId), data);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "Produto atualizado com sucesso!");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable UUID id, @RequestHeader("user-id") String userId) {
        this.service.deleteProduct(id, UUID.fromString(userId));

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "Produto excluído com sucesso.");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAll() {
        List<ProductResponseDTO> products = this.service.findAll();

        ApiResponse<List<ProductResponseDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), null, products);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
