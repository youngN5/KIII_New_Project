package mk.ukim.finki.lab03.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab03.model.Movie;
import mk.ukim.finki.lab03.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/servlet/movies")
public class MovieListServlet extends HttpServlet {
    private final MovieService movieService;

    private final SpringTemplateEngine springTemplateEngine;

    public MovieListServlet(MovieService movieService, SpringTemplateEngine springTemplateEngine) {
        this.movieService = movieService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context =  new WebContext(webExchange);

        String text= (String) req.getSession().getAttribute("searchText");
        String rating= (String) req.getSession().getAttribute("searchRating");

        List<Movie> movies = movieService.searchMoviesByParameter(text,rating);
        req.getSession().removeAttribute("searchText");
        req.getSession().removeAttribute("searchRating");
        context.setVariable("movies", movies);
        springTemplateEngine.process(
                "listMovies.html",
                context,
                resp.getWriter()
        );


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text=req.getParameter("search");
        String rating= req.getParameter("number");
        req.getSession().setAttribute("searchText",text);
        req.getSession().setAttribute("searchRating",rating);
        resp.sendRedirect("/servlet/movies");
    }

}
