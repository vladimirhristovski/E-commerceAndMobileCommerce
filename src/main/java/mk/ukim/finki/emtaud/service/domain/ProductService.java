package mk.ukim.finki.emtaud.service.domain;

import mk.ukim.finki.emtaud.model.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> save(Product product);

    Optional<Product> findById(Long id);

    Optional<Product> update(Long id, Product product);

    void deleteById(Long id);

    void refreshMaterializedView();

    Page<Product> findAll(Pageable pageable);

}
