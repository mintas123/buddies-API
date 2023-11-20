package pl.mroz.buddiesapi.interfaces.rest.rental;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mroz.buddiesapi.domain.account.Account;
import pl.mroz.buddiesapi.domain.common.Location;
import pl.mroz.buddiesapi.domain.rental.Rental;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class RentalDto {

    @Schema(example = "ff291c13-bd30-4950-a887-f0ab54c9eaf8", name = "Rental identification")
    private UUID rentalId;

    @Schema(example = "ff291c13-bd30-4950-a887-f0ab54c9eaf8", name = "Author identification")
    private UUID authorId;

    @Schema(example = "2 pokoje Warszawa Mokotów (GARAŻ)", name = "Title", required = true)
    @NonNull
    private final String title;

    @Schema(example = "false", name = "Is the price negotiable", required = true)
    private boolean isNegotiable;

    @Schema(example = "Mam na sprzedaż świeżo wyremontowane mieszkanie (...) ", name = "Detailed description", required = true, maxLength = 3000)
    @NonNull
    @Size(max = 3000)
    private String description;

    @Schema(example = "Warsaw, Poland", name = "textual location representation", required = true)
    @NonNull
    private String locationStr;

    @Schema(example = "21.017532", name = "Longitude", required = true)
    private double locationLng;

    @Schema(example = "52.237049", name = "Latitude", required = true)
    private double locationLat;

    @Schema(example = "2999", name = "Total price", required = true)
    private int price;

    @Schema(example = "3000", name = "Deposit", required = true)
    private int deposit;

    @Schema(example = "2", name = "Room count", required = true)
    private int rooms;

    @Schema(example = "5", name = "Floor the apartment is on", required = true)
    private int floor;

    @Schema(example = "48.45", name = "Apartment size", required = true)
    private double size;

//    @Schema(example = "110.14", name = "Price/size", required = true)
//    private double pricePerM;

    @Schema(example = "2009", name = "How old the building is", required = true)
    private int buildYear;

    @Schema(example = "2023-10-31T00:00:00.000-05:00", name = "Date when it will be available from", required = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NonNull
    private Instant rentDate;

//    @Schema(example = "['AC','Garage']", name = "Additional fatures of the rental property", required = true)
//    @NonNull
//    private Set<String> featureTags;
//
//    @Schema(example = "[https://pjatk-s16604-diploma-media.s3.eu-central-1.amazonaws.com/logotype.png]", name = "Set of urls with the uploaded pictures", required = true)
//    @NonNull
//    private Set<String> photoUrls;

    static RentalDto from(Rental domain) {
        return RentalDto.builder()
                .rentalId(domain.getRentalId())
                .authorId(domain.getAuthor().getAccountId())
                .title(domain.getTitle())
                .isNegotiable(domain.isNegotiable())
                .description(domain.getDescription())
                .locationStr(domain.getLocation().getReadableText())
                .locationLat(domain.getLocation().getLatitude())
                .locationLng(domain.getLocation().getLongitude())
                .price(domain.getPrice())
                .deposit(domain.getDeposit())
                .rooms(domain.getRooms())
                .floor(domain.getFloor())
                .size(domain.getSize())
//                .pricePerM(domain.getPricePerM())
                .buildYear(domain.getBuildYear())
                .rentDate(domain.getRentDate())
//                .featureTags(domain.getFeatureTags())
//                .photoUrls(domain.getPhotoUrls())
                .build();
    }

    static Rental toDomain(RentalDto dto, Account account) {
        var location = Location.builder()
                .readableText(dto.getLocationStr())
                .longitude(dto.getLocationLng())
                .latitude(dto.getLocationLat())
                .build();
        return Rental.builder()
                .title(dto.getTitle())
                .author(account)
                .isNegotiable(dto.isNegotiable())
                .description(dto.getDescription())
                .location(location)
                .price(dto.getPrice())
                .deposit(dto.getDeposit())
                .rooms(dto.getRooms())
                .floor(dto.getFloor())
                .size(dto.getSize())
                .buildYear(dto.getBuildYear())
                .rentDate(dto.getRentDate())
//                .featureTags(dto.getFeatures())
//                .photoUrls(dto.getPhotoList())
                .build();
    }
}
