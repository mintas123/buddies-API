package pl.mroz.buddiesapi.interfaces.rest.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mroz.buddiesapi.domain.rental.Rental;

@Getter
@AllArgsConstructor
public class RentalDto {

    //todo schema - OpenAPI support
    private final String title;

    static RentalDto from(Rental domainObject){
        return new RentalDto(domainObject.getTitle());
    }
}
