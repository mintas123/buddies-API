package pl.mroz.buddiesapi.domain.rental;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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

        String getLocationStr();

        double getLocationLng();

        double getLocationLat();

        int getPrice();

        int getDeposit();

        int getRooms();

        int getFloor();

        double getSize();

        double getPricePerM();

        int getBuildYear();

        LocalDate getRentDate();

        Set<String> getFeatureTags();

        Set<String> getPhotoUrls();

    }
}
