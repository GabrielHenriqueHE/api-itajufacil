package io.github.gabrielhenriquehe.itajufacil.repositories;

import io.github.gabrielhenriquehe.itajufacil.domain.product.Product;
import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:userId IS NULL OR p.user.id = :userId) AND " +
            "(:category IS NULL OR p.category = :category)")
    List<Product> findFilteredProducts(@Param("userId") UUID userId,
                                       @Param("category") ProductCategory category);

}
