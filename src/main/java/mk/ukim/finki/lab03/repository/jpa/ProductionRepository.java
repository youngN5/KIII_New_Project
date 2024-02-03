package mk.ukim.finki.lab03.repository.jpa;

import mk.ukim.finki.lab03.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<Production, Long> {
}
