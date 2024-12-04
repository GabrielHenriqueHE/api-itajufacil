package io.github.gabrielhenriquehe.itajufacil.utils;

import java.math.BigDecimal;

public class ProductDataValidator {

    public static boolean isValidName(String name) {
        return name != null &&
                !name.isEmpty() &&
                !name.isBlank() &&
                name.trim().length() > 3;
    }

    public static boolean isValidDescription(String description) {
        return description != null &&
                !description.isEmpty() &&
                !description.isBlank() &&
                description.trim().length() > 10;
    }

    public static boolean isValidPrice(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean isValidCategory(String category) {
        return category.equalsIgnoreCase("DOCES") ||
                category.equalsIgnoreCase("SALGADOS") ||
                category.equalsIgnoreCase("HORTIFRUTTI") ||
                category.equalsIgnoreCase("BEBIDAS");
    }

    public static boolean isValidSpecification(String specification) {
        return specification.equalsIgnoreCase("UN") || specification.equalsIgnoreCase("LT");
    }

}
