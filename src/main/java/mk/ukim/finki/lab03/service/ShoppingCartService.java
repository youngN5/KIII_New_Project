package mk.ukim.finki.lab03.service;

import mk.ukim.finki.lab03.model.ShoppingCart;
import mk.ukim.finki.lab03.model.TicketOrder;
import mk.ukim.finki.lab03.model.User;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    Optional<ShoppingCart> getLastShoppingCart(User user);
    List<TicketOrder> listAllTicketsInShoppingCart(Long cartId);

    ShoppingCart addTicketToShoppingCart(String username, Long ticketId);

}
