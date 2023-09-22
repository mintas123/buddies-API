package pl.mroz.buddiesapi.infrastructure.database.rental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;

@Configuration
public class RentalRepositoryConfig {

    @Bean
    RentalRepository rentalRepository(RentalRepositoryImpl.RentalJpaRepository jpaRepository) {
        return new RentalRepositoryImpl(jpaRepository);
    }
}
