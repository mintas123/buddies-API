package pl.mroz.buddiesapi.infrastructure.database.rental;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.mroz.buddiesapi.domain.common.ObjectMapper;
import pl.mroz.buddiesapi.domain.rental.Rental;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class RentalRepositoryInMemory implements RentalRepository {

    private final Set<Rental> rentals = new HashSet<>();

    @Override
    public List<Rental> getAllRentals() {
        return rentals.stream().toList();
    }

    @Override
    public Page<Rental> getByCriteria(Specification<RentalEntity> criteria, Pageable pageable) {
        //todo do criteria and sort
        var list = rentals.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .toList();
        return new PageImpl<>(list, pageable, rentals.size());
    }

    @Override
    public Rental getRentalById(UUID rentalId) {
        return rentals.stream()
                .filter(r -> r.getRentalId().equals(rentalId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No Rental with UUID: " + rentalId));
    }

    @Override
    public List<Rental> getAllRentalsFromUser(UUID accountId) {
        return rentals.stream()
                .filter(r -> r.getAuthor().getAccountId().equals(accountId))
                .toList();
    }

    @Override
    public List<String> getTopTags() {
        // todo get it from the collection
        return List.of("tag1", "tag2", "tag3");
    }

    @Override
    public Rental save(Rental rental) {
        if (rentals.contains(rental)) {
            Rental rentalById = getRentalById(rental.getRentalId());
            rentals.remove(rentalById);
            Rental updated = ObjectMapper.mapInto(rental, rentalById);
            rentals.add(updated);
            return updated;
        }
        rentals.add(rental);
        return rental;
    }

    @Override
    public void delete(Rental rental) {
        rentals.remove(getRentalById(rental.getRentalId()));
    }
}
