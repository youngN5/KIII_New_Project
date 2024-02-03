package mk.ukim.finki.lab03.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab03.model.Movie;
import mk.ukim.finki.lab03.model.TicketOrder;
import mk.ukim.finki.lab03.model.User;
import mk.ukim.finki.lab03.service.MovieService;
import mk.ukim.finki.lab03.service.ProductionService;
import mk.ukim.finki.lab03.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ProductionService productionService;
    private final TicketOrderService ticketOrderService;

    public MovieController(MovieService movieService, ProductionService productionService, TicketOrderService ticketOrderService) {
        this.movieService = movieService;
        this.productionService = productionService;
        this.ticketOrderService = ticketOrderService;
    }

    //TODO: Implement error
    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }
        model.addAttribute("movies", movieService.listAll());
        return "listMovies";
    }

    @PostMapping
    public String getMoviesByTitleAndRating(@RequestParam(required = false) String title,
                                            @RequestParam(required = false) String rating,
                                            Model model) {
        model.addAttribute("movies", movieService.searchMoviesByParameter(title, rating));
        return "listMovies";
    }

    @GetMapping("/add")
    public String saveMovie(Model model) {
        model.addAttribute("productions", productionService.findAll());
        return "add-movie";
    }

    @PostMapping("/save")
    public String save(@RequestParam String title,
                       @RequestParam String summary,
                       @RequestParam Double rating,
                       @RequestParam Long production) {
        movieService.saveMovie(title, summary, rating, production);
        return "redirect:/movies";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam String title,
                       @RequestParam String summary,
                       @RequestParam Double rating,
                       @RequestParam Long production
    ) {

        movieService.editMovie(id, title, summary, rating, production);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{movieId}")
    public String edit(@PathVariable Long movieId, Model model) {
        Optional<Movie> movie = movieService.findById(movieId);
        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
            model.addAttribute("productions", productionService.findAll());
            return "add-movie";
        }
        return "redirect:/movies?error=MovieNotFound";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        movieService.deleteById(id);
        return "redirect:/movies";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam(required = false) String selectedMovie,
                             @RequestParam String numTickets,
                             @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime created,
                             HttpServletRequest req) {

        User user = (User) req.getSession().getAttribute("user");
        TicketOrder ticket = ticketOrderService.placeOrder(selectedMovie, numTickets, user.getUsername(), created);
        if (ticket == null)
            return "redirect:/movies";

        return "redirect:/ticketOrder";
    }
}
