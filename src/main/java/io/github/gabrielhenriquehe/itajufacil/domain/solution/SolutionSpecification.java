package io.github.gabrielhenriquehe.itajufacil.domain.solution;

public enum SolutionSpecification {
    HORAS("HORAS"),
    MINUTOS("MINUTOS"),
    DIAS("DIAS"),
    SEMANAS("SEMANAS"),
    METROS("METROS");

    public String specification;

    SolutionSpecification(String specification) {
        this.specification = specification;
    }

    public String getSpecification() {
        return this.specification;
    }
}
