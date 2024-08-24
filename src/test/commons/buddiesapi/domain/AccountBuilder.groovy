package buddiesapi.domain

import buddiesapi.domain.account.Account
import buddiesapi.domain.account.PasswordHasher
import buddiesapi.domain.common.Location
import buddiesapi.domain.generation.RandomBuilder


class AccountBuilder implements RandomBuilder {


    private UUID accountId = randomUUID
    private String email = faker.internet().emailAddress()
    private PasswordHasher hasher = PasswordHasher.INSTANCE
    private String hashedPassword = hasher.hashPassword(hasher.generatePassword())
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
