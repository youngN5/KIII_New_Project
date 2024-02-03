package mk.ukim.finki.lab03.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @DateTimeFormat
    private LocalDateTime dateCreated;
    @OneToMany
    private List<TicketOrder> ticketOrders;

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        this.user = user;
        this.ticketOrders = new ArrayList<>();
    }

    public ShoppingCart() {

    }
}
