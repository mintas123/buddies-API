package pl.mroz.buddiesapi.infrastructure.database.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.mroz.buddiesapi.domain.rental.Rental;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentalRepository {

    private final RentalJpaRepository jpaRepository;

    @Override
    public int someMethod() {
        return 0;
    }

    public List<Rental> getAll() {
        return null;
    }

    interface RentalJpaRepository extends JpaRepository<Rental, Long> {

    }
}
