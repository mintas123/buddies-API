package buddiesapi.infrastructure.container

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    AccountConfig::class,
    RentalConfig::class
)
class DomainContainersConfig 
