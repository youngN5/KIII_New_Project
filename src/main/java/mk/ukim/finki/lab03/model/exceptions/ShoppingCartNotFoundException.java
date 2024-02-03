package mk.ukim.finki.lab03.model.exceptions;

public class ShoppingCartNotFoundException  extends RuntimeException{
    public ShoppingCartNotFoundException(Long cartId) {
        super(String.format("ShoppingCart with id %d doesn't exist", cartId));
    }
}
