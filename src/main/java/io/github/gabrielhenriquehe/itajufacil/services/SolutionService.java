package io.github.gabrielhenriquehe.itajufacil.services;

import io.github.gabrielhenriquehe.itajufacil.domain.solution.Solution;
import io.github.gabrielhenriquehe.itajufacil.domain.solution.SolutionCategory;
import io.github.gabrielhenriquehe.itajufacil.domain.solution.SolutionRegisterDTO;
import io.github.gabrielhenriquehe.itajufacil.domain.solution.SolutionSpecification;
import io.github.gabrielhenriquehe.itajufacil.exceptions.InvalidDataProvidedException;
import io.github.gabrielhenriquehe.itajufacil.repositories.SolutionRepository;
import io.github.gabrielhenriquehe.itajufacil.utils.SolutionDataValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    @Transactional
    public Solution registerSolution(SolutionRegisterDTO data) {

        this.validateSolutionData(data);

        SolutionCategory category = SolutionCategory.valueOf(data.category());
        SolutionSpecification specification = SolutionSpecification.valueOf(data.specification());

        Solution solution = new Solution(data.name(), data.description(), data.price(), category, specification);

        return this.solutionRepository.save(solution);
    }

    public List<Solution> findAll() {
        return this.solutionRepository.findAll();
    }

    private void validateSolutionData(SolutionRegisterDTO data) {
        if (!SolutionDataValidator.isValidName(data.name()))
            throw new InvalidDataProvidedException("Nome inválido.");
        if (!SolutionDataValidator.isValidDescription(data.description()))
            throw new InvalidDataProvidedException("Descrição inválida");
        if (!SolutionDataValidator.isValidPrice(data.price()))
            throw new InvalidDataProvidedException("Preço inválido.");
        if (!SolutionDataValidator.isValidCategory(data.category()))
            throw new InvalidDataProvidedException("Categoria inválida.");
        if (!SolutionDataValidator.isValidSpecification(data.specification()))
            throw new InvalidDataProvidedException("Especificação inválida.");
    }
}
