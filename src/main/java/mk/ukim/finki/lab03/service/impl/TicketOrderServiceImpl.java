package mk.ukim.finki.lab03.service.impl;

import mk.ukim.finki.lab03.model.TicketOrder;
import mk.ukim.finki.lab03.repository.jpa.TicketOrderRepository;
import mk.ukim.finki.lab03.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    private final TicketOrderRepository ticketOrderRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, String numberOfTickets, String username, LocalDateTime created) {
        if (numberOfTickets.isEmpty() || movieTitle == null || movieTitle.isEmpty()) {
            return null;
        }
        return ticketOrderRepository.save(new TicketOrder(movieTitle, Long.parseLong(numberOfTickets),username ,created));
    }


    @Override
    public List<TicketOrder> getTickets() {
        return ticketOrderRepository.findAll();
    }

    @Override
    public List<TicketOrder> getTicketsByMovieAndNumOfTickets(String movieTitle, String numOfTickets) {
        return ticketOrderRepository.findAllByMovieTitleAndNumberOfTickets(movieTitle, Long.parseLong(numOfTickets));
    }

    @Override
    public Optional<TicketOrder> findById(Long id) {
        return ticketOrderRepository.findById(id);
    }

    @Override
    public List<TicketOrder> findByUsername(String username) {
        return ticketOrderRepository.findByUsername(username);
    }

    @Override
    public List<TicketOrder> findAllByCreatedBetween(LocalDateTime from, LocalDateTime to) {
        return ticketOrderRepository.findAllByCreatedBetween(from,to);
    }
}
