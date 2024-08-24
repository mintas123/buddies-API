package buddiesapi.domain.account

import buddiesapi.infrastructure.database.location.LocationEntity
import java.util.UUID

interface AccountRepository {
    fun findAll(): List<Account>

    fun save(account: Account): Account

    fun delete(account: Account)

    fun getById(accountUUID: UUID): Account?

    interface IAccountEntity {
        val accountId: UUID?

        val email: String

        val hashedPassword: String

        val name: String

        val lastName: String

        val locationEntity: LocationEntity
    }
}
