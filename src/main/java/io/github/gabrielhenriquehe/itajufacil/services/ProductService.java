package io.github.gabrielhenriquehe.itajufacil.services;

import io.github.gabrielhenriquehe.itajufacil.domain.product.Product;
import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductCategory;
import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductRegisterDTO;
import io.github.gabrielhenriquehe.itajufacil.domain.product.ProductSpecification;
import io.github.gabrielhenriquehe.itajufacil.exceptions.InvalidDataProvidedException;
import io.github.gabrielhenriquehe.itajufacil.repositories.ProductRepository;
import io.github.gabrielhenriquehe.itajufacil.utils.ProductDataValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product registerProduct(ProductRegisterDTO data) {

        validateProductData(data);

        ProductCategory category = ProductCategory.valueOf(data.category().toUpperCase());
        ProductSpecification specification = ProductSpecification.valueOf(data.specification().toUpperCase());
        Product product = new Product(data.name(), data.description(), data.price(), category, specification);

        return this.productRepository.save(product);
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
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
