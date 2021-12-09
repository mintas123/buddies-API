package pl.mroz.buddiesapi.domain.rental;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public interface RentalRepository {

    int someMethod();
    // getAll, save, update,delete and all other

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

        int getSize();

        int getPricePerM();

        int getBuildYear();

        LocalDate getRentDate();

        Set<String> getFeatureTags();

        Set<String> getPhotoUrls();

    }
}
