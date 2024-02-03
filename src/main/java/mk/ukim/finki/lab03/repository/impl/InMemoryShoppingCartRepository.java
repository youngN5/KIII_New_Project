package mk.ukim.finki.lab03.repository.impl;

import mk.ukim.finki.lab03.bootstrap.DataHolder;
import mk.ukim.finki.lab03.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {
    public Optional<ShoppingCart> findById(Long id) {
        return DataHolder.shoppingCarts.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public Optional<ShoppingCart> findByUsername(String username) {
        return DataHolder.shoppingCarts.stream()
                .filter(i -> i.getUser().getUsername().equals(username))
                .findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart) {
        DataHolder.shoppingCarts
                .removeIf(i -> i.getUser().getUsername()
                        .equals(shoppingCart.getUser().getUsername()));
        DataHolder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

}
