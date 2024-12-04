package io.github.gabrielhenriquehe.itajufacil.domain.solution;

import java.math.BigDecimal;

public record SolutionPatchDTO(
        String name,
        String description,
        BigDecimal price,
        String category,
        String specification
) {
}
