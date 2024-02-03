package mk.ukim.finki.lab03.model.exceptions;

public class ShoppingCartDoesNotExist extends RuntimeException{
    public ShoppingCartDoesNotExist() {
        super("Shopping cart does not exist");
    }
}

