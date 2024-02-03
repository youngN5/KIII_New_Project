package mk.ukim.finki.lab03.repository.impl;

import mk.ukim.finki.lab03.bootstrap.DataHolder;
import mk.ukim.finki.lab03.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryTicketOrderRepository {
    public TicketOrder saveTicketOrder(TicketOrder ticketOrder) {
        DataHolder.ticketOrders.add(ticketOrder);
        return ticketOrder;
    }

    public Optional<TicketOrder> findById(Long id) {
        return DataHolder.ticketOrders.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public List<TicketOrder> getTicketOrders() {
        return DataHolder.ticketOrders;
    }

    public List<TicketOrder> getTicketsByMovieAndNumOfTickets(String movieTitle, String numOfTickets) {
        if (movieTitle == null || movieTitle.isEmpty() || numOfTickets == null || numOfTickets.isEmpty()) {
            return getTicketOrders();
        }
        Integer numTickets = Integer.parseInt(numOfTickets);
        return DataHolder.ticketOrders.stream()
                .filter(t -> t.getMovieTitle().contains(movieTitle))
                .filter(t -> t.getNumberOfTickets() > numTickets)
                .collect(Collectors.toList());
    }
}
