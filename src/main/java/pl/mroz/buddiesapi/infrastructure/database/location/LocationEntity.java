package pl.mroz.buddiesapi.infrastructure.database.location;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mroz.buddiesapi.domain.common.Location;

import java.util.UUID;

@Entity
@Table(name = "LOCATION")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID locationId;

    @Basic
    private String readableText;

    @Basic
    private double longitude;

    @Basic
    private double latitude;

    public static LocationEntity fromDomain(Location location) {
        return LocationEntity.builder()
                .locationId(location.getLocationId())
                .readableText(location.getReadableText())
                .longitude(location.getLongitude())
                .latitude(location.getLatitude())
                .build();

    }
}
