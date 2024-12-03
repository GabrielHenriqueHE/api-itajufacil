package io.github.gabrielhenriquehe.itajufacil.domain.solution;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_services")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Solution implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private SolutionCategory category;

    @Column(nullable = false)
    private SolutionSpecification specification;

    public Solution(String name, String description, BigDecimal price, SolutionCategory category, SolutionSpecification specification) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.specification = specification;
    }
}
