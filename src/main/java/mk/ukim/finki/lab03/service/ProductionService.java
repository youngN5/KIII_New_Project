package mk.ukim.finki.lab03.service;

import mk.ukim.finki.lab03.model.Production;

import java.util.List;
import java.util.Optional;

public interface ProductionService {
    List<Production> findAll();
    Optional<Production> saveProduction(String name, String country, String address);
    Optional<Production> editProduction(Long prodId, String name, String country, String address);

    Optional<Production> findById(Long prodId);

    void deleteById(Long prodId);
}
