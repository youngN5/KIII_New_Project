package mk.ukim.finki.lab03.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movieTitle;
    private Long numberOfTickets;
    private String username;
    @DateTimeFormat
    private LocalDateTime created;

    public TicketOrder(String movieTitle, Long numberOfTickets, String username, LocalDateTime created) {
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
        this.username = username;
        this.created = created;
    }

    public TicketOrder() {

    }
}
