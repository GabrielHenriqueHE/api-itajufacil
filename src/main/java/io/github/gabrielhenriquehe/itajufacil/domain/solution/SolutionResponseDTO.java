package io.github.gabrielhenriquehe.itajufacil.domain.solution;

import java.math.BigDecimal;

public record SolutionResponseDTO(
        String name,
        String description,
        BigDecimal price,
        SolutionCategory category,
        SolutionSpecification specification,
        String offeredBy
) {
}
