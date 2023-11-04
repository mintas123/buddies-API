package pl.mroz.buddiesapi.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.mroz.buddiesapi.infrastructure.database.location.LocationEntity;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
public class Location {
    private UUID locationId;

    private String readableText;

    private double longitude;

    private double latitude;

    public static Location fromDb(LocationEntity entity) {
        return Location.builder()
                .locationId(entity.getLocationId())
                .readableText(entity.getReadableText())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .build();
    }
}
