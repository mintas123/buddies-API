package buddiesapi.infrastructure.container

import buddiesapi.domain.rental.RentalDomainService
import buddiesapi.domain.rental.RentalDomainServiceFactory
import buddiesapi.domain.rental.RentalRepository
import org.springframework.context.annotation.Bean

class RentalConfig {
    @Bean
    fun rentalDomainService(repository: RentalRepository): RentalDomainService {
        return RentalDomainServiceFactory.rentalDomainService(repository)
    }
}
