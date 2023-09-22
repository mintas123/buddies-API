package pl.mroz.buddiesapi.infrastructure.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;
import pl.mroz.buddiesapi.domain.rental.RentalDomainService;
import pl.mroz.buddiesapi.domain.rental.RentalDomainServiceFactory;

@Configuration
public class RentalConfig {

    @Bean
    RentalDomainService rentalDomainService(RentalRepository repository) {
        return RentalDomainServiceFactory.rentalDomainService(repository);
    }
}
