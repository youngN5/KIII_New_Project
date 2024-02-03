package mk.ukim.finki.lab03.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab03.model.TicketOrder;
import mk.ukim.finki.lab03.model.User;
import mk.ukim.finki.lab03.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = "/servlet/ticketOrder")
public class TicketOrderServlet extends HttpServlet {
    private final TicketOrderService ticketOrderService;
    private final SpringTemplateEngine springTemplateEngine;

    public TicketOrderServlet(TicketOrderService ticketOrderService, SpringTemplateEngine springTemplateEngine) {
        this.ticketOrderService = ticketOrderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        String movieTitle = (String) req.getSession().getAttribute("movieTitle");
        String numOfTickets = (String) req.getSession().getAttribute("numOfTickets");
        List<TicketOrder> ticketOrders = ticketOrderService.getTicketsByMovieAndNumOfTickets(movieTitle, numOfTickets);

        context.setVariable("tickets", ticketOrders);
        springTemplateEngine.process(
                "orderConfirmation.html",
                context,
                resp.getWriter()
        );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("movieTitle", req.getParameter("movieTitle"));
        req.getSession().setAttribute("numOfTickets", req.getParameter("numOfTickets"));

        String clientName = req.getParameter("clientName");
        String movieTitle = req.getParameter("selectedMovie");
        String clientAddress = req.getRemoteAddr();
        String numTickets = req.getParameter("numTickets");
        String created = req.getParameter("created");
        User user = (User) req.getSession().getAttribute("user");

        TicketOrder ticket = this.ticketOrderService.placeOrder(movieTitle, numTickets, user.getUsername(), LocalDateTime.parse(created));
        if (ticket == null) {
            resp.sendRedirect("/servlet/movies");
            return;
        }
        resp.sendRedirect("/servlet/ticketOrder");
    }
}
