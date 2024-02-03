package mk.ukim.finki.lab03.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab03.model.TicketOrder;
import mk.ukim.finki.lab03.model.User;
import mk.ukim.finki.lab03.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;

    public TicketOrderController(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @GetMapping
    public String getTicketOrdersPage(Model model, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        List<TicketOrder> tickets = ticketOrderService.getTickets().stream()
                .filter(t -> t.getUsername().equals(user.getUsername()))
                .collect(Collectors.toList());
        model.addAttribute("tickets", tickets);
        return "orderConfirmation";
    }

    @PostMapping
    public String getTicketsOrdersByTitleAndNumberOfTickets(@RequestParam String movieTitle,
                                                            @RequestParam String numOfTickets,
                                                            Model model) {
        model.addAttribute("tickets", ticketOrderService.getTicketsByMovieAndNumOfTickets(movieTitle, numOfTickets));
        return "orderConfirmation";
    }

    @PostMapping("/between")
    public String getTicketsFromToDates(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime from,
                                        @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime to,
                                        Model model) {
        model.addAttribute("tickets", ticketOrderService.findAllByCreatedBetween(from, to));
        return "orderConfirmation";
    }
}
