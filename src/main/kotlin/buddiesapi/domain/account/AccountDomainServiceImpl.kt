package buddiesapi.domain.account

import buddiesapi.domain.common.ObjectMapper
import java.util.UUID
import mu.KLogging

class AccountDomainServiceImpl(val repository: AccountRepository) : AccountDomainService {

    override val allAccounts: List<Account> = repository.findAll()

    override fun getAccount(accountId: UUID): Account? =
        repository.getById(accountId)

    override fun createAccount(account: Account): Account {
        return repository.save(account)
    }

    override fun updateAccount(uuid: UUID, account: Account): Account =
        repository.save(ObjectMapper.mapInto(account, getAccount(uuid)!!))

    override fun deleteAccount(account: Account) =
        repository.delete(account)

    companion object : KLogging()
}
