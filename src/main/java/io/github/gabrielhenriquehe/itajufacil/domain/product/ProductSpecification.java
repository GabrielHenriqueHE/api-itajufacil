package io.github.gabrielhenriquehe.itajufacil.domain.product;

public enum ProductSpecification {
    UN("UN"),
    LT("LT");

    private String specification;

    ProductSpecification(String specification) {
        this.specification = specification;
    }

    public String getSpecification() {
        return this.specification;
    }
}
