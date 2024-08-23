package buddiesapi.domain.account

import buddiesapi.domain.common.Location
import java.util.UUID

data class Account(
    val accountId: UUID,
    val email: String,
    val hashedPassword: String,
    val name: String,
    val lastName: String,
    val location: Location
) {

    companion object {
        fun fromDb(entity: AccountRepository.IAccountEntity): Account {
            return entity.let {
                Account(
                    it.accountId!!,
                    it.email,
                    it.hashedPassword,
                    it.name,
                    it.lastName,
                    Location.fromDb(it.locationEntity)
                )
            }

        }
    }
}
