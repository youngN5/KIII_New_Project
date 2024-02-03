package mk.ukim.finki.lab03.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.lab03.model.Movie;
import mk.ukim.finki.lab03.model.Production;
import mk.ukim.finki.lab03.model.exceptions.ProductionNotFoundException;
import mk.ukim.finki.lab03.repository.jpa.MovieRepository;
import mk.ukim.finki.lab03.repository.jpa.ProductionRepository;
import mk.ukim.finki.lab03.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ProductionRepository productionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.findMoviesByTitleLike(text);
    }

    @Override
    public List<Movie> searchMoviesByParameter(String text, String rating) {
        return movieRepository.findMoviesByTitleLikeAndRatingEquals(text, Double.parseDouble(rating));
    }

    @Override
    public Optional<Movie> saveMovie(String title, String summary, Double rating, Long production) {
        Production prod = productionRepository.findAll().stream()
                .filter(p -> p.getId().equals(production))
                .findFirst()
                .orElseThrow(() -> new ProductionNotFoundException(production));

        return Optional.of(movieRepository.save(new Movie(title, summary, rating, prod)));
    }

    @Override
    @Transactional
    public Optional<Movie> editMovie(Long movieId, String title, String summary, Double rating, Long production) {
        Production prod = productionRepository.findAll().stream()
                .filter(p -> p.getId().equals(production))
                .findFirst()
                .orElseThrow(() -> new ProductionNotFoundException(production));
        this.deleteById(movieId);

        return Optional.of(movieRepository.save(new Movie(title, summary, rating, prod)));
    }

    @Override
    public Optional<Movie> findById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public void deleteById(Long movieId) {
        movieRepository.deleteById(movieId);
    }

}
