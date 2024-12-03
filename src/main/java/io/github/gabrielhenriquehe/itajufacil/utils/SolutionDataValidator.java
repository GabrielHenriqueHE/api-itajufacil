package io.github.gabrielhenriquehe.itajufacil.utils;

import java.math.BigDecimal;

public class SolutionDataValidator {

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
        return price != null && Double.parseDouble(price.toString()) > 0.00;
    }

    public static boolean isValidCategory(String category) {
        return category.equalsIgnoreCase("JARDINAGEM") ||
                category.equalsIgnoreCase("MARCENARIA") ||
                category.equalsIgnoreCase("PINTUAR");
    }

    public static boolean isValidSpecification(String specification) {
        return specification.equalsIgnoreCase("HORAS") ||
                specification.equalsIgnoreCase("MINUTOS") ||
                specification.equalsIgnoreCase("METROS") ||
                specification.equalsIgnoreCase("DIAS") ||
                specification.equalsIgnoreCase("SEMANAS");
    }

}
