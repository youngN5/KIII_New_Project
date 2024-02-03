package mk.ukim.finki.lab03.repository.impl;


import mk.ukim.finki.lab03.bootstrap.DataHolder;
import mk.ukim.finki.lab03.model.Movie;
import mk.ukim.finki.lab03.model.Production;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryMovieRepository {
    public List<Movie> findAll() {
        return DataHolder.movies;
    }

    public List<Movie> searchMovies(String text) {
        return DataHolder.movies.stream()
                .filter(m -> m.getTitle().contains(text) || m.getSummary().contains(text))
                .collect(Collectors.toList());
    }

    public List<Movie> searchMoviesByParameter(String text, String rating) {
        if ((text == null || text.isEmpty()) && (rating == null || rating.isEmpty())) {
            return this.findAll();
        }
        return DataHolder.movies.stream()
                .filter(m -> text != null && m.getTitle().contains(text))
                .filter(m -> {
                    if (rating != null) {
                        try {
                            double parsedRating = Double.parseDouble(rating);
                            return m.getRating() >= parsedRating;
                        } catch (NumberFormatException e) {
                            // Handle the case where 'rating' is not a valid number
                            return true; // or take appropriate action
                        }
                    }
                    return false; // or take appropriate action when 'rating' is null
                })
                .collect(Collectors.toList());

    }

    public Optional<Movie> saveMovie(String title, String summary, Double rating, Production production) {
        if (production == null) {
            throw new IllegalArgumentException();
        }
        Movie movie = new Movie(title, summary, rating, production);
        DataHolder.movies.removeIf(m -> m.getTitle().equals(title));
        DataHolder.movies.add(movie);
        return Optional.of(movie);
    }

    public Optional<Movie> editMovie(Long movieId, String title, String summary, Double rating, Production production) {
        if (production == null) {
            throw new IllegalArgumentException();
        }
        Movie movie = new Movie(title, summary, rating, production);
        DataHolder.movies.removeIf(m -> m.getId().equals(movieId));
        DataHolder.movies.add(movie);
        return Optional.of(movie);
    }

    public Optional<Movie> findById(Long movieId) {
        return DataHolder.movies.stream()
                .filter(m -> m.getId().equals(movieId))
                .findFirst();
    }

    public void deleteById(Long movieId) {
        DataHolder.movies.removeIf(m -> m.getId().equals(movieId));
    }
}
