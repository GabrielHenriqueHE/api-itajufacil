package io.github.gabrielhenriquehe.itajufacil.domain.product;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_products")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product implements Serializable {

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
    private ProductCategory category;

    @Column(nullable = false)
    private ProductSpecification specification;

    public Product(String name, String description, BigDecimal price, ProductCategory category, ProductSpecification specification) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.specification = specification;
    }

}
