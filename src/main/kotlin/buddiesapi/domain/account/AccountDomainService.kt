package buddiesapi.domain.account

import java.util.UUID


interface AccountDomainService {
    fun getAllAccounts(): List<Account>

    fun getAccount(accountId: UUID): Account?

    fun createAccount(account: Account): Account

    fun updateAccount(uuid: UUID, account: Account): Account

    fun deleteAccount(account: Account)
}
