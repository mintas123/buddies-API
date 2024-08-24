package buddiesapi.infrastructure.database.account

import buddiesapi.domain.account.Account
import buddiesapi.domain.account.AccountRepository
import buddiesapi.infrastructure.database.account.AccountEntity.Companion.fromDomain
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

class AccountRepositoryImpl(private val jpaRepository: AccountJpaRepository) : AccountRepository {

    override fun findAll(): List<Account> = jpaRepository.findAll().stream()
        .map { entity: AccountEntity? -> entity?.let { Account.fromDb(it) } }
        .toList()


    override fun getById(accountUUID: UUID): Account? =
        jpaRepository.getByAccountId(accountUUID)?.let {
            Account.fromDb(it)
        }

    override fun save(account: Account): Account {
        val savedEntity = jpaRepository.save(fromDomain(account))
        return Account.fromDb(savedEntity)
    }

    override fun delete(account: Account) =
        jpaRepository.delete(fromDomain(account))

    interface AccountJpaRepository :
        JpaRepository<AccountEntity, UUID> {
        fun getByAccountId(accountId: UUID): AccountEntity?
    }
}
