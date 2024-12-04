package io.github.gabrielhenriquehe.itajufacil.controllers;

import io.github.gabrielhenriquehe.itajufacil.domain.solution.SolutionPatchDTO;
import io.github.gabrielhenriquehe.itajufacil.domain.solution.SolutionRegisterDTO;
import io.github.gabrielhenriquehe.itajufacil.domain.solution.SolutionResponseDTO;
import io.github.gabrielhenriquehe.itajufacil.dto.ApiResponse;
import io.github.gabrielhenriquehe.itajufacil.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/solutions")
public class SolutionController {

    @Autowired
    private SolutionService service;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<Void>> registerSolution(@RequestBody SolutionRegisterDTO data, @RequestHeader("user-id") String id) {
        UUID userId = UUID.fromString(id);
        this.service.registerSolution(data, userId);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Serviço criado com sucesso!");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<ApiResponse<Void>> patchSolution(@PathVariable UUID id, @RequestBody SolutionPatchDTO data, @RequestHeader("user-id") String userId) {
        this.service.patchSolution(id, UUID.fromString(userId), data);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "Serviço atualizado com sucesso!");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<Void>> deleteSolution(@PathVariable UUID id, @RequestHeader("user-id") String userId) {
        this.service.deleteSolution(id, UUID.fromString(userId));

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "Serviço excluído com sucesso!");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SolutionResponseDTO>>> findAll() {
        var data = this.service.findAll();

        ApiResponse<List<SolutionResponseDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), null, data);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
