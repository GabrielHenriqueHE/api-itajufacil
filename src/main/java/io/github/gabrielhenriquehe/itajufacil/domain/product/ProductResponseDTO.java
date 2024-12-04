package io.github.gabrielhenriquehe.itajufacil.domain.product;

import java.math.BigDecimal;

public record ProductResponseDTO(
        String name,
        String description,
        BigDecimal price,
        ProductCategory category,
        ProductSpecification specification,
        String offeredBy
) {
}
