package mk.ukim.finki.emtaud.service.impl;

import mk.ukim.finki.emtaud.model.Product;
import mk.ukim.finki.emtaud.model.dto.ProductDto;
import mk.ukim.finki.emtaud.repository.ProductRepository;
import mk.ukim.finki.emtaud.service.CategoryService;
import mk.ukim.finki.emtaud.service.ManufacturerService;
import mk.ukim.finki.emtaud.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ManufacturerService manufacturerService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> save(ProductDto product) {
        if (product.getCategory() != null &&
                this.categoryService.findById(product.getCategory()).isPresent() &&
                product.getManufacturer() != null &&
                this.manufacturerService.findById(product.getManufacturer()).isPresent()) {
            return Optional.of(
                    this.productRepository.save(new Product(product.getName(), product.getPrice(), product.getQuantity(),
                            this.categoryService.findById(product.getCategory()).get(),
                            this.manufacturerService.findById(product.getManufacturer()).get()))
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> update(Long id, ProductDto product) {
        return this.productRepository.findById(id).map(existingProduct -> {
            if (product.getName() != null) {
                existingProduct.setName(product.getName());
            }
            if (product.getPrice() != null) {
                existingProduct.setPrice(product.getPrice());
            }
            if (product.getQuantity() != null) {
                existingProduct.setQuantity(product.getQuantity());
            }
            if (product.getCategory() != null && this.categoryService.findById(product.getCategory()).isPresent()) {
                existingProduct.setCategory(this.categoryService.findById(product.getCategory()).get());
            }
            if (product.getManufacturer() != null && this.manufacturerService.findById(product.getManufacturer()).isPresent()) {
                existingProduct.setManufacturer(this.manufacturerService.findById(product.getManufacturer()).get());
            }
            return this.productRepository.save(existingProduct);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
