package mk.ukim.finki.emtaud.service.application;

import mk.ukim.finki.emtaud.dto.DisplayProductDto;
import mk.ukim.finki.emtaud.dto.ShoppingCartDto;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartApplicationService {

    List<DisplayProductDto> listAllProductsInShoppingCart(Long cartId);

    Optional<ShoppingCartDto> getActiveShoppingCart(String username);

    Optional<ShoppingCartDto> addProductToShoppingCart(String username, Long productId);


}
