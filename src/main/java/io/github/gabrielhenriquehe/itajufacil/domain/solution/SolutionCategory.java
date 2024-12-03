package io.github.gabrielhenriquehe.itajufacil.domain.solution;

public enum SolutionCategory {
    JARDINAGEM("JARDINAGEM"),
    MARCENARIA("MARCENARIA"),
    PINTURA("PINTURA");

    public String category;

    SolutionCategory(String category) {
        this.category = category;
    }

    public String getCategory(String category) {
        return this.category;
    }
}
