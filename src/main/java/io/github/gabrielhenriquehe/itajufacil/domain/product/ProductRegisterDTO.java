package io.github.gabrielhenriquehe.itajufacil.domain.product;

import lombok.NonNull;

import java.math.BigDecimal;

public record ProductRegisterDTO(
        @NonNull String name,
        @NonNull String description,
        @NonNull BigDecimal price,
        @NonNull String category,
        @NonNull String specification
        ) {

}
