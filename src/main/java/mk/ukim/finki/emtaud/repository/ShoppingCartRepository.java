package mk.ukim.finki.emtaud.repository;

import mk.ukim.finki.emtaud.model.domain.ShoppingCart;
import mk.ukim.finki.emtaud.model.domain.User;
import mk.ukim.finki.emtaud.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
