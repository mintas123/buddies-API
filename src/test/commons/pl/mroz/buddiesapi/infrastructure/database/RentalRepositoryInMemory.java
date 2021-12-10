package pl.mroz.buddiesapi.infrastructure.database;

import pl.mroz.buddiesapi.domain.rental.Rental;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;
import pl.mroz.buddiesapi.infrastructure.database.rental.RentalEntity;

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
    public Rental getRentalById(UUID rentalId) {
        return rentals.stream()
                .filter(r -> r.getRentalId().equals(rentalId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No Rental with UUID: " + rentalId));
    }

    @Override
    public List<String> getTopTags() {
        // todo get it from the collection
        return List.of("tag1", "tag2", "tag3");
    }

    @Override
    public Rental save(Rental rental) {
        if (rentals.contains(rental)) {
            throw new IllegalStateException("Cannot save, rental with " + rental.getRentalId() + " already exists");
        }
        rentals.add(rental);
        return rental;
    }

    @Override
    public Rental update(Rental rental) {
        var rentalById = getRentalById(rental.getRentalId());
        var copyEntity = RentalEntity.builder()
                .rentalId(rentalById.getRentalId())
                //.creator(accountService.getById(rental.getAccountId()))
                .title(rental.getTitle())
                .isNegotiable(rental.isNegotiable())
                .description(rental.getDescription())
                .locationStr(rental.getLocationStr())
                .locationLat(rental.getLocationLat())
                .locationLng(rental.getLocationLng())
                .price(rental.getPrice())
                .deposit(rental.getDeposit())
                .rooms(rental.getRooms())
                .floor(rental.getFloor())
                .size(rental.getSize())
                .buildYear(rental.getBuildYear())
                .rentDate(rental.getRentDate())
                .featureTags(rental.getFeatureTags())
                .photoUrls(rental.getPhotoUrls())
                .build();

        rentals.remove(rentalById);
        rentals.add(Rental.fromDb(copyEntity));

        return rental;
    }

    @Override
    public void delete(Rental rental) {
        rentals.remove(getRentalById(rental.getRentalId()));
    }
}
