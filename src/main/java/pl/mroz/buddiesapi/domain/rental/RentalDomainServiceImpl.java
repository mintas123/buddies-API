package pl.mroz.buddiesapi.domain.rental;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.mroz.buddiesapi.infrastructure.database.rental.RentalEntity;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
class RentalDomainServiceImpl implements RentalDomainService {

    private final RentalRepository repository;

    @Override
    public List<Rental> getAllRentals() {
        return repository.getAllRentals();
    }

    @Override
    public Page<Rental> getByCriteria(RentalRepository.IRentalCriteria rentalCriteria, Pageable pageable) {
        Specification<RentalEntity> spec = Specification.where(null);
        //todo https://medium.com/@bubu.tripathy/dynamic-query-with-specification-interface-in-spring-data-jpa-ae8764e32162
        return repository.getByCriteria(spec, pageable);
    }

    @Override
    public List<Rental> getRentalsFromUser(UUID accountId) {
        return repository.getAllRentalsFromUser(accountId);
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
