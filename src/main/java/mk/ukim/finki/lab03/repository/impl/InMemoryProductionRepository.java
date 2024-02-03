package mk.ukim.finki.lab03.repository.impl;

import mk.ukim.finki.lab03.bootstrap.DataHolder;
import mk.ukim.finki.lab03.model.Production;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductionRepository {
    public List<Production> findAll() {
        return DataHolder.productions;
    }

    public Optional<Production> saveProduction(String name, String country, String address) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        Production production = new Production(name, country, address);
        DataHolder.productions.removeIf(m -> m.getName().equals(name));
        DataHolder.productions.add(production);
        return Optional.of(production);
    }

    public Optional<Production> findById(Long prodId) {
        return DataHolder.productions.stream().
                filter(p -> p.getId().equals(prodId))
                .findFirst();
    }

    public void deleteById(Long prodId) {
        DataHolder.productions.removeIf(p -> p.getId().equals(prodId));
    }

    public Optional<Production> editProduction(Long prodId, String name, String country, String address) {
        if (prodId == null) {
            throw new IllegalArgumentException();
        }
        Production production=this.findById(prodId).get();
        production.setName(name);
        production.setCountry(country);
        production.setAddress(address);

        return Optional.of(production);
    }
}
