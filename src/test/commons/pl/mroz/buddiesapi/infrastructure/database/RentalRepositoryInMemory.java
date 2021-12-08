package pl.mroz.buddiesapi.infrastructure.database;

import pl.mroz.buddiesapi.domain.rental.Rental;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;

import java.util.HashSet;
import java.util.Set;

public class RentalRepositoryInMemory implements RentalRepository {

    private final Set<Rental> rentals = new HashSet<>();

    @Override
    public int someMethod() {
        return -1;
    }
}
