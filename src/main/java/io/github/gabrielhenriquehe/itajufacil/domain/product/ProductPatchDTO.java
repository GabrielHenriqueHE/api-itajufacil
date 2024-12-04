package io.github.gabrielhenriquehe.itajufacil.domain.product;

import java.math.BigDecimal;

public record ProductPatchDTO(
        String name,
        String description,
        BigDecimal price,
        String category,
        String specification
) {

}
