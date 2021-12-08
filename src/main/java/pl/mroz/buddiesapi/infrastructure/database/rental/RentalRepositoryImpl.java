package pl.mroz.buddiesapi.infrastructure.database.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;

@Component
@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentalRepository {

    @Override
    public int someMethod() {
        return 0;
    }
}
