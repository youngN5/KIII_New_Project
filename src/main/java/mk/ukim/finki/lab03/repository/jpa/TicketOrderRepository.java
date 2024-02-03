package mk.ukim.finki.lab03.repository.jpa;

import mk.ukim.finki.lab03.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
    List<TicketOrder> findAll();
    List<TicketOrder> findAllByMovieTitleAndNumberOfTickets(String movieTitle, Long numberOfTickets);
    List<TicketOrder> findByUsername(String username);
    List<TicketOrder> findAllByCreatedBetween(LocalDateTime from, LocalDateTime to);
}
