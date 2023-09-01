package pl.mroz.buddiesapi.infrastructure.database.account

import pl.mroz.buddiesapi.domain.account.Account
import pl.mroz.buddiesapi.domain.account.AccountRepository

class AccountRepositoryInMemory implements AccountRepository {

    def accounts = []

    def withAccounts(List<AccountEntity> entities) {
        accounts = entities
    }

    @Override
    List<Account> findAll() {
        return accounts
    }
}
