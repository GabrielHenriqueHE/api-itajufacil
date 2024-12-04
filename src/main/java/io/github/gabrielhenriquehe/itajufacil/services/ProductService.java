package io.github.gabrielhenriquehe.itajufacil.services;

import io.github.gabrielhenriquehe.itajufacil.domain.product.*;
import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductResponseDTO;
import io.github.gabrielhenriquehe.itajufacil.exceptions.ForbiddenOperationException;
import io.github.gabrielhenriquehe.itajufacil.exceptions.InvalidDataProvidedException;
import io.github.gabrielhenriquehe.itajufacil.exceptions.ResourceNotFoundException;
import io.github.gabrielhenriquehe.itajufacil.repositories.ProductRepository;
import io.github.gabrielhenriquehe.itajufacil.utils.ProductDataValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public Product registerProduct(ProductRegisterDTO data, UUID id) {

        validateProductData(data);

        var user = this.userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado"));

        ProductCategory category = ProductCategory.valueOf(data.category().toUpperCase());
        ProductSpecification specification = ProductSpecification.valueOf(data.specification().toUpperCase());
        Product product = new Product(data.name(), data.description(), data.price(), category, specification, user);

        return this.productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(UUID productId, UUID userId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não localizado."));
        var user = this.userService.findUserById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado."));

        if (!product.getUser().equals(user)) throw new ForbiddenOperationException("Operação não permitida: o usuário não é o dono deste produto.");

        this.productRepository.delete(product);
    }

    @Transactional
    public Product patchProduct(UUID productId, UUID userId, ProductPatchDTO data) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não localizado."));
        var user = this.userService.findUserById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado."));

        if (!product.getUser().equals(user)) throw new ForbiddenOperationException("Operação não permitida: o usuário não é o dono deste produto.");

        if (data.name() != null) {
            if (!ProductDataValidator.isValidName(data.name())) {
                throw new InvalidDataProvidedException("Nome inválido.");
            } else {
                product.setName(data.name());
            }
        }

        if (data.description() != null) {
            if (!ProductDataValidator.isValidDescription(data.description())) {
                product.setDescription(data.description());
                throw new InvalidDataProvidedException("Descrição inválida");
            } else {
                product.setDescription(data.description());
            }
        }

        if (data.price() != null) {
            if (!ProductDataValidator.isValidPrice(data.price())) {
                throw new InvalidDataProvidedException("Preço inválido.");
            } else {
                product.setPrice(data.price());
            }
        }

        if (data.category() != null) {
            if (!ProductDataValidator.isValidCategory(data.category())) {
                throw new InvalidDataProvidedException("Categoria inválida.");
            } else {
                product.setCategory(ProductCategory.valueOf(data.category()));
            }
        }

        if (data.specification() != null) {
            if (!ProductDataValidator.isValidSpecification(data.specification())) {
                throw new InvalidDataProvidedException("Especificação inválida.");
            } else {
                product.setSpecification(ProductSpecification.valueOf(data.specification()));
            }
        }

        return this.productRepository.save(product);
    }

    public List<ProductResponseDTO> findProducts(UUID userId, String category) {

        List<Product> rawProducts;

        if (userId != null && category != null) {
            var user = this.userService.findUserById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado."));

            rawProducts = this.productRepository.findFilteredProducts(userId, ProductCategory.valueOf(category.toUpperCase()));
        } else if (userId != null) {
            var user = this.userService.findUserById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado."));

            rawProducts = this.productRepository.findFilteredProducts(userId, null);
        } else if (category != null) {
            rawProducts = this.productRepository.findFilteredProducts(null, ProductCategory.valueOf(category.toUpperCase()));
        } else {
            rawProducts = this.productRepository.findFilteredProducts(null, null);
        }

        if (!rawProducts.isEmpty()) {
            return rawProducts.stream().map(p -> new ProductResponseDTO(
                    p.getName(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getCategory(),
                    p.getSpecification(),
                    p.getUser().getUsername()
            )).toList();
        }

        return new ArrayList<>();
    }

    private void validateProductData(ProductRegisterDTO data) {
        if (!ProductDataValidator.isValidName(data.name()))
            throw new InvalidDataProvidedException("Nome inválido.");
        if (!ProductDataValidator.isValidDescription(data.description()))
            throw new InvalidDataProvidedException("Descrição inválida");
        if (!ProductDataValidator.isValidPrice(data.price()))
            throw new InvalidDataProvidedException("Preço inválido.");
        if (!ProductDataValidator.isValidCategory(data.category()))
            throw new InvalidDataProvidedException("Categoria inválida.");
        if (!ProductDataValidator.isValidSpecification(data.specification()))
            throw new InvalidDataProvidedException("Especificação inválida.");
    }
}
