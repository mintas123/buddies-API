package pl.mroz.buddiesapi.infrastructure.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;
import pl.mroz.buddiesapi.domain.rental.RentalProvider;
import pl.mroz.buddiesapi.domain.rental.RentalProviderFactory;

@Configuration
public class RentalConfig {

    @Bean
    RentalProvider rentalService(RentalRepository repository) {
        return new RentalProviderFactory().rentalService(repository);
    }
}
