package mk.ukim.finki.lab03.repository.jpa;

import mk.ukim.finki.lab03.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMoviesByTitleLike(String text);
    List<Movie> findMoviesByTitleLikeAndRatingEquals(String title, double rating);

}
