package buddiesapi.infrastructure.database.rental

import buddiesapi.domain.rental.RentalRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RentalRepositoryConfig {
    @Bean
    fun rentalRepository(jpaRepository: RentalRepositoryImpl.RentalJpaRepository): RentalRepository {
        return RentalRepositoryImpl(jpaRepository)
    }
}
