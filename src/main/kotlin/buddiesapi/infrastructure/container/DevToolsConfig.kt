package buddiesapi.infrastructure.container

import buddiesapi.domain.account.AccountDomainService
import buddiesapi.domain.account.PasswordHasher
import buddiesapi.domain.rental.RentalDomainService
import buddiesapi.infrastructure.devtools.DevDataLoader
import buddiesapi.infrastructure.devtools.DevelopmentFeature
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
//    DevelopmentFeature::class,
    DomainContainersConfig::class
)
class DevToolsConfig {

    @Bean
    fun devDataLoader(
        developmentFeature: DevelopmentFeature,
        accountDomainService: AccountDomainService,
        rentalDomainService: RentalDomainService,
    ): DevDataLoader = DevDataLoader(developmentFeature, accountDomainService, rentalDomainService, PasswordHasher)

}