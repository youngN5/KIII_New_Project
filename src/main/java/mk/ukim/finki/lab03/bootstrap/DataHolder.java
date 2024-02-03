package mk.ukim.finki.lab03.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab03.model.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
public class DataHolder {
    public static List<Movie> movies = null;
    public static List<TicketOrder> ticketOrders = null;
    public static List<Production> productions = null;
    public static List<ShoppingCart> shoppingCarts = null;
    public static List<User> users = null;

    @PostConstruct
    public void init() {
//        movies = new ArrayList<>();
//        ticketOrders = new ArrayList<>();
//        productions = new ArrayList<>();
//        shoppingCarts = new ArrayList<>();
//        users = new ArrayList<>();
//
//        productions.add(new Production("Production 1", "Country 1", "Address 1"));
//        productions.add(new Production("Production 2", "Country 2", "Address 2"));
//        productions.add(new Production("Production 3", "Country 3", "Address 3"));
//        productions.add(new Production("Production 4", "Country 4", "Address 4"));
//        productions.add(new Production("Production 5", "Country 5", "Address 5"));
//
//        movies.add(new Movie("Title 1", "Summary 1", 4.5, productions.get(0)));
//        movies.add(new Movie("Title 2", "Summary 2", 3.5, productions.get(1)));
//        movies.add(new Movie("Title 3", "Summary 3", 2.0, productions.get(2)));
//        movies.add(new Movie("Title 4", "Summary 4", 5.0, productions.get(3)));
//        movies.add(new Movie("Title 5", "Summary 5", 2.5, productions.get(4)));
//
//        users.add(new User("kostadin.mishev", "km", "Kostadin", "Mishev", LocalDate.of(1990,5,14)));
//        users.add(new User("ana.todorovska", "at", "Ana", "Todorovska",LocalDate.of(1990,1,18)));
//        users.add(new User("milena.trajanoska", "mt", "Milena", "Trajanoska",LocalDate.of(1990,9,23)));
//        users.add(new User("aleksandar.petrushevski", "ap", "Aleksandar", "Petrushevski",LocalDate.of(1990,6,8)));

    }
}
