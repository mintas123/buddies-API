package pl.mroz.buddiesapi.interfaces.rest.rental;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import pl.mroz.buddiesapi.domain.rental.Rental;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class RentalDto {

    //todo schema - OpenAPI support
    @NonNull
    private UUID rentalId;

    @NonNull
    private final String title;

    private boolean isNegotiable;

    @NonNull
    private String description;

    @NonNull
    private String locationStr;

    private double locationLng;

    private double locationLat;

    private int price;

    private int deposit;

    private int rooms;

    private int floor;

    private int size;

    private int pricePerM;

    private int buildYear;

    @NonNull
    private LocalDate rentDate;

    @NonNull
    private Set<String> featureTags;

    @NonNull
    private Set<String> photoUrls;

    static RentalDto from(Rental domain) {
        return RentalDto.builder()
                .rentalId(domain.getRentalId())
                .title(domain.getTitle())
                .isNegotiable(domain.isNegotiable())
                .description(domain.getDescription())
                .locationStr(domain.getLocationStr())
                .locationLat(domain.getLocationLat())
                .locationLng(domain.getLocationLng())
                .price(domain.getPrice())
                .deposit(domain.getDeposit())
                .rooms(domain.getRooms())
                .floor(domain.getFloor())
                .size(domain.getSize())
                .pricePerM(domain.getPricePerM())
                .buildYear(domain.getBuildYear())
                .rentDate(domain.getRentDate())
                .featureTags(domain.getFeatureTags())
                .photoUrls(domain.getPhotoUrls())
                .build();
    }
}
