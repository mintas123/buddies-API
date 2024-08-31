package buddiesapi.infrastructure.devtools

import buddiesapi.domain.account.Account
import buddiesapi.domain.account.AccountDomainService
import buddiesapi.domain.account.PasswordHasher
import buddiesapi.domain.common.Location
import buddiesapi.domain.rental.Rental
import buddiesapi.domain.rental.RentalDomainService
import jakarta.annotation.PostConstruct
import java.time.Instant
import java.util.UUID
import org.springframework.transaction.annotation.Transactional

open class DevDataLoader(
    private val feature: DevelopmentFeature,
    private val accountDomainService: AccountDomainService,
    private val rentalDomainService: RentalDomainService,
    private val passwordHasher: PasswordHasher,
) {

    @PostConstruct
    @Transactional
    open fun initDb() {
        if (!feature.mockData) return

        val account = Account(
            UUID.randomUUID(),
            "jankowalski@gmail.com",
            passwordHasher.hashPassword("test"),
            "Jan",
            "Kowalski",
            Location(UUID.randomUUID(), "Warszawa", 15.15, 20.20)
        )
        val rental = Rental(
            "Rental!!!",
            UUID.randomUUID(),
            account,
            false,
            "lengthy description",
            Location(UUID.randomUUID(), "Warszawa Śródmieście", 15.15, 20.20),
            3000,
            3000,
            2,
            1,
            50.5,
            2020,
            Instant.now()
        )
//        accountDomainService.createAccount(account) todo fix doubling entities
        rentalDomainService.createRental(rental)
    }
}