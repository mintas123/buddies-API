package pl.mroz.buddiesapi.domain.rental;

import pl.mroz.buddiesapi.infrastructure.database.location.LocationEntity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface RentalRepository {

    List<Rental> getAllRentals();

    Rental getRentalById(UUID rentalId);

    List<String> getTopTags();

    Rental save(Rental rental);

    Rental update(Rental rental);

    void delete(Rental rental);

    interface IRentalEntity {

        UUID getRentalId();

        String getTitle();

        boolean isNegotiable();

        String getDescription();

        LocationEntity getLocationEntity();

        int getPrice();

        int getDeposit();

        int getRooms();

        int getFloor();

        double getSize();

//        double getPricePerM();

        int getBuildYear();

        Instant getRentDate();

//        Set<String> getFeatureTags();

//        Set<String> getPhotoUrls();

    }
}
