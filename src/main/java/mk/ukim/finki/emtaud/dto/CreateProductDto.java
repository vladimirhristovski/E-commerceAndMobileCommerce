package mk.ukim.finki.emtaud.dto;

import mk.ukim.finki.emtaud.model.domain.Category;
import mk.ukim.finki.emtaud.model.domain.Manufacturer;
import mk.ukim.finki.emtaud.model.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

public record CreateProductDto(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

    public static CreateProductDto from(Product product) {
        return new CreateProductDto(product.getName(), product.getPrice(), product.getQuantity(), product.getCategory().getId(), product.getManufacturer().getId());
    }

    public static List<CreateProductDto> from(List<Product> products) {
        return products.stream().map(CreateProductDto::from).collect(Collectors.toList());
    }

    public Product toProduct(Category category, Manufacturer manufacturer) {
        return new Product(name, price, quantity, category, manufacturer);
    }
}
