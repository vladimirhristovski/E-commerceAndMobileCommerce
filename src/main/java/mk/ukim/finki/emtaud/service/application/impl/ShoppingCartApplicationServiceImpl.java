package mk.ukim.finki.emtaud.service.application.impl;

import mk.ukim.finki.emtaud.dto.DisplayProductDto;
import mk.ukim.finki.emtaud.dto.ShoppingCartDto;
import mk.ukim.finki.emtaud.service.application.ShoppingCartApplicationService;
import mk.ukim.finki.emtaud.service.domain.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartApplicationServiceImpl implements ShoppingCartApplicationService {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartApplicationServiceImpl(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public List<DisplayProductDto> listAllProductsInShoppingCart(Long cartId) {
        return DisplayProductDto.from(this.shoppingCartService.listAllProductsInShoppingCart(cartId));
    }

    @Override
    public Optional<ShoppingCartDto> getActiveShoppingCart(String username) {
        return this.shoppingCartService.getActiveShoppingCart(username).map(ShoppingCartDto::from);
    }

    @Override
    public Optional<ShoppingCartDto> addProductToShoppingCart(String username, Long productId) {
        return this.shoppingCartService.addProductToShoppingCart(username, productId).map(ShoppingCartDto::from);
    }
}
