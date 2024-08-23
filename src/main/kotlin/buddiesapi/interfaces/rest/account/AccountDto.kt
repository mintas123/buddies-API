package buddiesapi.interfaces.rest.account

import buddiesapi.domain.account.Account
import buddiesapi.domain.common.Location
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID


data class AccountDto(
    @Schema(example = "ff291c13-bd30-4950-a887-f0ab54c9eaf8", name = "Account identification")
    val accountId: UUID,

    @Schema(example = "jan.kowalski@gmail.com", name = "Account email address")
    val email: String?,

    @Schema(example = "ad2dc90ed2fceee06f7c8989b33e5509fd76334c639c53ee588529f1517858a2", name = "Hash of password")
    val hashedPassword: String?,

    @Schema(example = "Jan", name = "First name")
    val name: String?,

    @Schema(example = "Kowalski", name = "Last name")
    val lastName: String?,

    @Schema(example = "Warsaw, Poland", name = "Location readable text")
    val locationStr: String?,

    @Schema(example = "21.017532", name = "Longitude")
    val locationLng: Double?,

    @Schema(example = "52.237049", name = "Latitude")
    val locationLat: Double?,
) {


    companion object {
        fun from(domain: Account): AccountDto =
            domain.let {
                AccountDto(
                    it.accountId,
                    it.email,
                    it.hashedPassword,
                    it.name,
                    it.lastName,
                    it.location.readableText,
                    it.location.longitude,
                    it.location.latitude
                )
            }

        fun toDomain(dto: AccountDto): Account =
            //todo think about nullability
            dto.let {
                Account(
                    it.accountId,
                    it.email!!,
                    it.hashedPassword!!,
                    it.name!!,
                    it.lastName!!,
                    Location(null, it.locationStr!!, it.locationLng!!, it.locationLat!!)
                )
            }
    }
}
