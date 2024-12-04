package io.github.gabrielhenriquehe.itajufacil.dto;

import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductCategory;
import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductSpecification;

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
