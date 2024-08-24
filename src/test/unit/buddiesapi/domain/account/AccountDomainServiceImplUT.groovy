package buddiesapi.domain.account

import buddiesapi.domain.generation.RandomBuilder
import buddiesapi.infrastructure.database.account.AccountRepositoryInMemory
import spock.lang.Specification
import spock.lang.Subject

class AccountDomainServiceImplUT extends Specification implements RandomBuilder {

    private final PasswordHasher hasher = PasswordHasher.INSTANCE

    def repository = new AccountRepositoryInMemory()

    @Subject
    def provider = AccountDomainServiceFactory.accountDomainService(repository)

    def 'should return list of all accounts'() {
        given:
            def accounts = [
                    new Account(UUID.randomUUID(), 'test@email.com', hasher.hashPassword(hasher.generatePassword()), 'John', 'Deer', randomLocation),
                    new Account(UUID.randomUUID(), 'mail@gmail.com', hasher.hashPassword(hasher.generatePassword()), 'John', 'Kowalski', randomLocation),
            ]
            repository.withAccounts(accounts)
        when:
            def result = provider.getAllAccounts()
        then:
            result.size() == 2
            result.get(0).email == 'test@email.com'
            result.get(1).lastName == 'Kowalski'
    }

    def 'should save an account'() {
        given:
            def toStore = new Account(UUID.randomUUID(), 'test@email.com', hasher.hashPassword(hasher.generatePassword()), 'John', 'Deer', randomLocation)
        when:
            provider.createAccount(toStore)
        then:
            repository.accounts.size() == 1
            with(repository.accounts.first()) {
                it.email == 'test@email.com'
                it.lastName == 'Deer'
            }
    }
}
