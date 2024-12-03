package io.github.gabrielhenriquehe.itajufacil.controllers;

import io.github.gabrielhenriquehe.itajufacil.domain.solution.Solution;
import io.github.gabrielhenriquehe.itajufacil.domain.solution.SolutionRegisterDTO;
import io.github.gabrielhenriquehe.itajufacil.dto.ApiResponse;
import io.github.gabrielhenriquehe.itajufacil.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solutions")
public class SolutionController {

    @Autowired
    private SolutionService service;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<Void>> registerSolution(@RequestBody SolutionRegisterDTO data) {
        this.service.registerSolution(data);

        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Serviço criado com sucesso!");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Solution>>> findAll() {
        var data = this.service.findAll();

        ApiResponse<List<Solution>> response = new ApiResponse<>(HttpStatus.OK.value(), null, data);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
