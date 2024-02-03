package mk.ukim.finki.lab03.service.impl;

import mk.ukim.finki.lab03.model.Production;
import mk.ukim.finki.lab03.repository.jpa.ProductionRepository;
import mk.ukim.finki.lab03.service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final ProductionRepository productionRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Production> findAll() {
        return productionRepository.findAll();
    }

    @Override
    public Optional<Production> saveProduction(String name, String country, String address) {
        return Optional.of(productionRepository.save(new Production(name, country, address)));
    }

    @Override
    public Optional<Production> editProduction(Long prodId, String name, String country, String address) {
        this.deleteById(prodId);
        return this.saveProduction(name, country, address);
    }

    @Override
    public Optional<Production> findById(Long prodId) {
        return productionRepository.findById(prodId);
    }

    @Override
    public void deleteById(Long prodId) {
        productionRepository.deleteById(prodId);
    }
}
