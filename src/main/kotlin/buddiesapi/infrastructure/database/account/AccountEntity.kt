package buddiesapi.infrastructure.database.account

import buddiesapi.domain.account.Account
import buddiesapi.domain.account.AccountRepository
import buddiesapi.infrastructure.database.location.LocationEntity
import jakarta.persistence.Basic
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.util.UUID


@Entity
@Table(name = "ACCOUNT")
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var accountId: UUID? = null,

    @Basic
    override val email: @Email(message = "Invalid email") String,

    @Basic
    override val hashedPassword: @NotBlank String,

    @Basic
    override val name: @NotBlank String,

    @Basic
    override val lastName: @NotBlank String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "location_id")
    override val locationEntity: LocationEntity
) : AccountRepository.IAccountEntity {
    
    companion object {
        @JvmStatic
        fun fromDomain(domain: Account): AccountEntity =
            domain.let {
                AccountEntity(
                    it.accountId,
                    it.email,
                    it.hashedPassword,
                    it.name,
                    it.lastName,
                    LocationEntity.fromDomain(it.location)
                )
            }
    }
}
