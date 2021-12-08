package pl.mroz.buddiesapi.infrastructure.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;
import pl.mroz.buddiesapi.domain.rental.RentalService;
import pl.mroz.buddiesapi.domain.rental.RentalServiceImpl;

@Configuration
public class RentalServiceConfig {

    @Bean
    RentalService rentalService(RentalRepository repository) {
        return new RentalServiceImpl(repository);
    }
}
