package io.github.gabrielhenriquehe.itajufacil.repositories;

import io.github.gabrielhenriquehe.itajufacil.domain.solution.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SolutionRepository extends JpaRepository<Solution, UUID> {



}
