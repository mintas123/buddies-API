package pl.mroz.buddiesapi.domain.rental;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import pl.mroz.buddiesapi.domain.common.Location;

import java.time.Instant;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Rental {

    @NonNull
    private UUID rentalId;

    @NonNull
    private final String title;

    private boolean isNegotiable;

    @NonNull
    private String description;

    @NonNull
    private Location location;

    private int price;

    private int deposit;

    private int rooms;

    private int floor;

    private double size;

//    private double pricePerM;

    private int buildYear;

    @NonNull
    private Instant rentDate;

//    @NonNull
//    private Set<String> featureTags;
//
//    @NonNull
//    private Set<String> photoUrls;

    public static Rental fromDb(RentalRepository.IRentalEntity entity) {
        return Rental.builder()
                .rentalId(entity.getRentalId())
                .title(entity.getTitle())
                .isNegotiable(entity.isNegotiable())
                .description(entity.getDescription())
                .location(Location.fromDb(entity.getLocationEntity()))
                .price(entity.getPrice())
                .deposit(entity.getDeposit())
                .rooms(entity.getRooms())
                .floor(entity.getFloor())
                .size(entity.getSize())
//                .pricePerM(entity.getPricePerM())
                .buildYear(entity.getBuildYear())
                .rentDate(entity.getRentDate())
//                .featureTags(entity.getFeatureTags())
//                .photoUrls(entity.getPhotoUrls())
                .build();
    }

}
