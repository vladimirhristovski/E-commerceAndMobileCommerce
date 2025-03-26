package mk.ukim.finki.emtaud.repository;

import mk.ukim.finki.emtaud.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
