package mk.ukim.finki.emtaud.service;

import mk.ukim.finki.emtaud.model.Product;
import mk.ukim.finki.emtaud.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> save(ProductDto product);

    Optional<Product> findById(Long id);

    Optional<Product> update(Long id, ProductDto product);

    void deleteById(Long id);

}
