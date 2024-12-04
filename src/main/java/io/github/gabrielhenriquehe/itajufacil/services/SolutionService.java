package io.github.gabrielhenriquehe.itajufacil.services;

import io.github.gabrielhenriquehe.itajufacil.domain.solution.*;
import io.github.gabrielhenriquehe.itajufacil.exceptions.ForbiddenOperationException;
import io.github.gabrielhenriquehe.itajufacil.exceptions.InvalidDataProvidedException;
import io.github.gabrielhenriquehe.itajufacil.exceptions.ResourceNotFoundException;
import io.github.gabrielhenriquehe.itajufacil.repositories.SolutionRepository;
import io.github.gabrielhenriquehe.itajufacil.utils.SolutionDataValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public Solution registerSolution(SolutionRegisterDTO data, UUID id) {

        validateSolutionData(data);

        var user = this.userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado."));

        SolutionCategory category = SolutionCategory.valueOf(data.category());
        SolutionSpecification specification = SolutionSpecification.valueOf(data.specification());

        Solution solution = new Solution(data.name(), data.description(), data.price(), category, specification, user);

        return this.solutionRepository.save(solution);
    }

    @Transactional
    public void deleteSolution(UUID solutionId, UUID userId) {
        Solution solution = this.solutionRepository.findById(solutionId).orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado."));
        var user = this.userService.findUserById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuráio não localizado."));

        if (!solution.getUser().equals(user)) throw new ForbiddenOperationException("Operação não permitida: o usuário não é o dono deste serviço.");

        this.solutionRepository.delete(solution);
    }

    @Transactional
    public Solution patchSolution(UUID solutionId, UUID userId, SolutionPatchDTO data) {
        Solution solution = this.solutionRepository.findById(solutionId).orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado."));
        var user = this.userService.findUserById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuráio não localizado."));

        if (!solution.getUser().equals(user)) throw new ForbiddenOperationException("Operação não permitida: o usuário não é o dono deste produto.");

        if (data.name() != null) {
            if (!SolutionDataValidator.isValidName(data.name())) {
                throw new InvalidDataProvidedException("Nome inválido.");
            } else {
                solution.setName(data.name());
            }
        }

        if (data.description() != null) {
            if (!SolutionDataValidator.isValidDescription(data.description())) {
                solution.setDescription(data.description());
                throw new InvalidDataProvidedException("Descrição inválida");
            } else {
                solution.setDescription(data.description());
            }
        }

        if (data.price() != null) {
            if (!SolutionDataValidator.isValidPrice(data.price())) {
                throw new InvalidDataProvidedException("Preço inválido.");
            } else {
                solution.setPrice(data.price());
            }
        }

        if (data.category() != null) {
            if (!SolutionDataValidator.isValidCategory(data.category())) {
                throw new InvalidDataProvidedException("Categoria inválida.");
            } else {
                solution.setCategory(SolutionCategory.valueOf(data.category()));
            }
        }

        if (data.specification() != null) {
            if (!SolutionDataValidator.isValidSpecification(data.specification())) {
                throw new InvalidDataProvidedException("Especificação inválida.");
            } else {
                solution.setSpecification(SolutionSpecification.valueOf(data.specification()));
            }
        }

        return this.solutionRepository.save(solution);
    }

    public List<SolutionResponseDTO> findAll() {
        List<Solution> rawSolutions = this.solutionRepository.findAll();

        return rawSolutions.stream().map(s -> new SolutionResponseDTO(
                s.getName(),
                s.getDescription(),
                s.getPrice(),
                s.getCategory(),
                s.getSpecification(),
                s.getUser().getUsername()
        )).toList();

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
