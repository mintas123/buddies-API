package pl.mroz.buddiesapi.domain

import pl.mroz.buddiesapi.domain.account.Account
import pl.mroz.buddiesapi.domain.account.PasswordHasher
import pl.mroz.buddiesapi.domain.common.Location
import pl.mroz.buddiesapi.domain.generation.RandomBuilder

class AccountBuilder implements RandomBuilder {


    private UUID accountId = randomUUID
    private String email = faker.internet().emailAddress()
    private String hashedPassword = PasswordHasher.hashPassword(PasswordHasher.generatePassword())
    private String name = faker.name().firstName()
    private String lastName = faker.name().lastName()
    private Location location = randomLocation

    AccountBuilder withAccountId(UUID uuid) {
        accountId = uuid
        return this
    }

    AccountBuilder withEmail(String email) {
        this.email = email
        return this
    }

    Account build() {
        return new Account(accountId, email, hashedPassword, name, lastName, location)
    }

}
