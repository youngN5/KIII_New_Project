package mk.ukim.finki.lab03.model.exceptions;

public class TicketOrderNotFoundException extends RuntimeException {
    public TicketOrderNotFoundException(Long ticketId) {
        super(String.format("TicketOrder with id %d doesn't exist", ticketId));
    }
}
