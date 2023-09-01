package pl.mroz.buddiesapi.domain.rental;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
class RentalProviderImpl implements RentalProvider {

    private final RentalRepository repository;

    @Override
    public List<Rental> getAllRentals() {
        return repository.getAllRentals();
    }

    @Override
    public List<Rental> getRentalsFromUser(UUID accountId) {
        throw new NotImplementedException();
    }

    @Override
    public Rental getRental(UUID rentalId) {
        return repository.getRentalById(rentalId);
    }

    @Override
    public List<String> getTopTags() {
        return repository.getTopTags();
    }

    @Override
    public Rental createRental(Rental rental) {
        return repository.save(rental);
    }

    @Override
    public Rental updateRental(Rental rental) {
        return repository.update(rental);
    }

    @Override
    public void deleteRental(Rental rental) {
        log.info("Rental deleted. UUID: {}", rental.getRentalId());
        // todo remove files from the cloud when ready
        repository.delete(rental);
    }
}
