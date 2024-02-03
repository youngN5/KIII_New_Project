package mk.ukim.finki.lab03.model.exceptions;

public class TicketAlreadyInShoppingCartException extends RuntimeException{
    public TicketAlreadyInShoppingCartException(Long ticketId,String username) {
        super(String.format("Ticket with id %d and created by user with username %s already exist in shopping cart", ticketId,username));
    }
}
