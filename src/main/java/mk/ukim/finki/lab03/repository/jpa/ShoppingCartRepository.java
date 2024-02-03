package mk.ukim.finki.lab03.repository.jpa;

import mk.ukim.finki.lab03.model.ShoppingCart;
import mk.ukim.finki.lab03.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUser(User user);

}
