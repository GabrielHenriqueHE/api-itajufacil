package io.github.gabrielhenriquehe.itajufacil.domain.solution;

import lombok.NonNull;

import java.math.BigDecimal;

public record SolutionRegisterDTO(
        @NonNull String name,
        @NonNull String description,
        @NonNull BigDecimal price,
        @NonNull String category,
        @NonNull String specification
        ) {

}
