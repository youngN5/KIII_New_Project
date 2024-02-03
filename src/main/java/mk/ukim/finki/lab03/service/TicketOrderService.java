package mk.ukim.finki.lab03.service;

import mk.ukim.finki.lab03.model.TicketOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketOrderService {

    TicketOrder placeOrder(String movieTitle, String numberOfTickets, String username, LocalDateTime created);

    List<TicketOrder> getTickets();

    List<TicketOrder> getTicketsByMovieAndNumOfTickets(String movieTitle, String numOfTickets);

    Optional<TicketOrder> findById(Long id);

    List<TicketOrder> findByUsername(String username);
    List<TicketOrder> findAllByCreatedBetween(LocalDateTime from, LocalDateTime to);
}
