package mk.ukim.finki.emtaud.service.domain.impl;

import mk.ukim.finki.emtaud.model.domain.Product;
import mk.ukim.finki.emtaud.repository.ProductRepository;
import mk.ukim.finki.emtaud.repository.ProductsPerManufacturerViewRepository;
import mk.ukim.finki.emtaud.service.domain.CategoryService;
import mk.ukim.finki.emtaud.service.domain.ManufacturerService;
import mk.ukim.finki.emtaud.service.domain.ProductService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;
    private final ProductsPerManufacturerViewRepository productsPerManufacturerViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ProductServiceImpl(ProductRepository productRepository, ManufacturerService manufacturerService, CategoryService categoryService, ProductsPerManufacturerViewRepository productsPerManufacturerViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
        this.productsPerManufacturerViewRepository = productsPerManufacturerViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> save(Product product) {
        Optional<Product> savedProduct = Optional.empty();

        if (product.getCategory() != null &&
                this.categoryService.findById(product.getCategory().getId()).isPresent() &&
                product.getManufacturer() != null &&
                this.manufacturerService.findById(product.getManufacturer().getId()).isPresent()) {
            savedProduct = Optional.of(this.productRepository.save(new Product(
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    this.categoryService.findById(product.getCategory().getId()).get(),
                    this.manufacturerService.findById(product.getManufacturer().getId()).get()
            )));
            this.refreshMaterializedView();
//            this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        }
        return savedProduct;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
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
            if (product.getCategory() != null && this.categoryService.findById(product.getCategory().getId()).isPresent()) {
                existingProduct.setCategory(this.categoryService.findById(product.getCategory().getId()).get());
            }
            if (product.getManufacturer() != null && this.manufacturerService.findById(product.getManufacturer().getId()).isPresent()) {
                existingProduct.setManufacturer(this.manufacturerService.findById(product.getManufacturer().getId()).get());
            }
            Product updatedProduct = this.productRepository.save(existingProduct);

            this.refreshMaterializedView();
//            this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(product));

            return updatedProduct;
        });
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
        this.productsPerManufacturerViewRepository.refreshMaterializedView();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }
}
