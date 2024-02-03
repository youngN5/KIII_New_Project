package mk.ukim.finki.lab03.service.impl;

import mk.ukim.finki.lab03.model.ShoppingCart;
import mk.ukim.finki.lab03.model.TicketOrder;
import mk.ukim.finki.lab03.model.User;
import mk.ukim.finki.lab03.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.lab03.model.exceptions.TicketAlreadyInShoppingCartException;
import mk.ukim.finki.lab03.model.exceptions.TicketOrderNotFoundException;
import mk.ukim.finki.lab03.model.exceptions.UserNotFoundException;
import mk.ukim.finki.lab03.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.lab03.repository.jpa.TicketOrderRepository;
import mk.ukim.finki.lab03.repository.jpa.UserRepository;
import mk.ukim.finki.lab03.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final TicketOrderRepository ticketOrderService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, TicketOrderRepository ticketOrderService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.ticketOrderService = ticketOrderService;
    }


    @Override
    public Optional<ShoppingCart> getLastShoppingCart(User user) {
        return shoppingCartRepository.findByUser(user);
    }

    @Override
    public List<TicketOrder> listAllTicketsInShoppingCart(Long cartId) {
        Optional<ShoppingCart> shoppingCartOptional = this.shoppingCartRepository.findById(cartId);

        if (shoppingCartOptional.isEmpty()) {
            throw new ShoppingCartNotFoundException(cartId);
        }

        return shoppingCartOptional.get().getTicketOrders();
    }


    @Override
    public ShoppingCart addTicketToShoppingCart(String username, Long ticketId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException(username));
        TicketOrder ticket = this.ticketOrderService.findById(ticketId)
                .orElseThrow(() -> new TicketOrderNotFoundException(ticketId));
        ShoppingCart shoppingCart=this.shoppingCartRepository.findByUser(user)
                .orElseGet(()->{
                    return new ShoppingCart(user);
                });

        List<TicketOrder> ticketOrdersInShoppingCart = shoppingCart.getTicketOrders().stream()
                .filter(i -> i.getId().equals(ticketId)).toList();

        if (ticketOrdersInShoppingCart.size() > 0) {
            throw new TicketAlreadyInShoppingCartException(ticketId, username);
        }

        shoppingCart.getTicketOrders().add(ticket);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
