package pl.mroz.buddiesapi.domain.rental;

import java.util.List;
import java.util.UUID;

public interface RentalDomainService {

    List<Rental> getAllRentals();

    List<Rental> getRentalsFromUser(UUID accountId);

    Rental getRental(UUID rentalId);

    List<String> getTopTags();

    Rental createRental(Rental rental);

    Rental updateRental(Rental rental);

    void deleteRental(Rental rental);
}
