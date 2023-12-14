package pl.mroz.buddiesapi.domain.rental;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RentalDomainService {

    List<Rental> getAllRentals();

    Page<Rental> getByCriteria(RentalRepository.IRentalCriteria rentalCriteria, Pageable pageable);

    List<Rental> getRentalsFromUser(UUID accountId);

    Rental getRental(UUID rentalId);

    List<String> getTopTags();

    Rental createRental(Rental rental);

    Rental updateRental(UUID uuid, Rental rental);

    void deleteRental(Rental rental);
}
