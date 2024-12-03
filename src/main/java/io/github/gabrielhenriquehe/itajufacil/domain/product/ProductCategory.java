package io.github.gabrielhenriquehe.itajufacil.domain.product;

public enum ProductCategory {
    DOCES("DOCES"),
    SALGADOS("SALGADOS"),
    HORTIFRUTTI("HORTIFRUTTI"),
    BEBIDAS("BEBIDAS");

    private String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
