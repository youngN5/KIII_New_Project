package mk.ukim.finki.lab03.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(urlPatterns = "/servlet/filteredTicketOrders")
public class FilteredTicketOrders extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("movieTitle",req.getParameter("movieTitle"));
        req.getSession().setAttribute("numOfTickets",req.getParameter("numOfTickets"));
        resp.sendRedirect("/servlet/ticketOrder");
    }
}
