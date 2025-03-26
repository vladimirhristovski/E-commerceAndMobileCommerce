package mk.ukim.finki.emtaud.model.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException(Long id) {
        super(String.format("Shopping cart with id: %s was not found", id));
    }
}
