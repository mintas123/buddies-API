package pl.mroz.buddiesapi.domain.account

import pl.mroz.buddiesapi.infrastructure.database.account.AccountEntity
import pl.mroz.buddiesapi.infrastructure.database.account.AccountRepositoryInMemory
import spock.lang.Specification
import spock.lang.Subject

class AccountProviderImplUT extends Specification {

    def repository = new AccountRepositoryInMemory()

    @Subject
    def provider = AccountProviderFactory.accountProvider(repository)

    def 'should return list of all accounts'() {
        given:
            def listOfEntities = [
                    new AccountEntity(UUID.randomUUID(), 'test@email.com', PasswordHasher.hashPassword(PasswordHasher.generatePassword()), 'John', 'Deer'),
                    new AccountEntity(UUID.randomUUID(), 'mail@gmail.com', PasswordHasher.hashPassword(PasswordHasher.generatePassword()), 'John', 'Kowalski'),
            ]
            repository.withAccounts(listOfEntities)
        when:
            def result = provider.getAllAccounts()
        then:
            result.size() == 2
            result.get(0).email == 'test@email.com'
            result.get(1).lastName == 'Kowalski'

    }
}
