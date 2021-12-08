package pl.mroz.buddiesapi.domain.rental;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Rental {
    @NonNull
    private final String title;

    public static Rental fromDb(RentalRepository.IRentalEntity entity) {
        return new Rental(entity.getTitle());
    }

}
