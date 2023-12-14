package pl.mroz.buddiesapi.domain.rental;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.mroz.buddiesapi.domain.common.Location;
import pl.mroz.buddiesapi.infrastructure.database.account.AccountEntity;
import pl.mroz.buddiesapi.infrastructure.database.location.LocationEntity;
import pl.mroz.buddiesapi.infrastructure.database.rental.RentalEntity;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface RentalRepository {

    List<Rental> getAllRentals();

    Page<Rental> getByCriteria(Specification<RentalEntity> criteria, Pageable pageable);

    Rental getRentalById(UUID rentalId);

    List<Rental> getAllRentalsFromUser(UUID accountId);

    List<String> getTopTags();

    Rental save(Rental rental);

    void delete(Rental rental);

    interface IRentalEntity {

        UUID getRentalId();

        AccountEntity getAuthorEntity();

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

    interface IRentalCriteria {

        int getMaxDistance();

        Location getLocation();

        int getPriceMin();

        int getPriceMax();

        int getSizeMin();

        int getSizeMax();

        int getRoomsMin();

        int getRoomsMax();

        int getBuildMin();

        int getBuildMax();

        int getMoveMin();

        int getMoveMax();

        Set<String> getFeatures();
    }
}
