package mk.ukim.finki.lab03.model.exceptions;

public class ProductionNotFoundException extends RuntimeException{
    public ProductionNotFoundException(Long productionId) {
        super(String.format("Production with id %d doesn't exist", productionId));
    }
}
