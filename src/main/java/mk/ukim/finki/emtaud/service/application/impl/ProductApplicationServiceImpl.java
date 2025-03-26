package mk.ukim.finki.emtaud.service.application.impl;

import mk.ukim.finki.emtaud.dto.CreateProductDto;
import mk.ukim.finki.emtaud.dto.DisplayProductDto;
import mk.ukim.finki.emtaud.model.domain.Category;
import mk.ukim.finki.emtaud.model.domain.Manufacturer;
import mk.ukim.finki.emtaud.service.application.ProductApplicationService;
import mk.ukim.finki.emtaud.service.domain.CategoryService;
import mk.ukim.finki.emtaud.service.domain.ManufacturerService;
import mk.ukim.finki.emtaud.service.domain.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductApplicationServiceImpl implements ProductApplicationService {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductApplicationServiceImpl(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public Optional<DisplayProductDto> update(Long id, CreateProductDto productDto) {
        Optional<Manufacturer> manufacturer = this.manufacturerService.findById(productDto.manufacturerId());
        Optional<Category> category = this.categoryService.findById(productDto.categoryId());

        return this.productService.update(id,
                productDto.toProduct(
                        category.orElse(null),
                        manufacturer.orElse(null)
                )
        ).map(DisplayProductDto::from);
    }

    @Override
    public Optional<DisplayProductDto> save(CreateProductDto productDto) {
        Optional<Manufacturer> manufacturer = this.manufacturerService.findById(productDto.manufacturerId());
        Optional<Category> category = this.categoryService.findById(productDto.categoryId());

        if (manufacturer.isPresent() && category.isPresent()) {
            return this.productService.save(productDto.toProduct(category.get(), manufacturer.get())).map(DisplayProductDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayProductDto> findById(Long id) {
        return this.productService.findById(id).map(DisplayProductDto::from);
    }

    @Override
    public List<DisplayProductDto> findAll() {
        return this.productService.findAll().stream().map(DisplayProductDto::from).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        this.productService.deleteById(id);
    }
}
