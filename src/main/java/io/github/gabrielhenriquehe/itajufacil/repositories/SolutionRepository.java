package io.github.gabrielhenriquehe.itajufacil.repositories;

import io.github.gabrielhenriquehe.itajufacil.domain.solution.Solution;
import io.github.gabrielhenriquehe.itajufacil.domain.solution.SolutionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SolutionRepository extends JpaRepository<Solution, UUID> {

    @Query("SELECT s FROM Solution s WHERE " +
            "(:userId IS NULL OR s.user.id = :userId) AND " +
            "(:category IS NULL OR s.category = :category)")
    List<Solution> findFilteredSolutions(@Param("userId") UUID userId,
                                         @Param("category")SolutionCategory category);

}
