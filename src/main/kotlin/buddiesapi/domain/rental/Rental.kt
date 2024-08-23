package buddiesapi.domain.rental

import buddiesapi.domain.account.Account
import buddiesapi.domain.common.Location
import java.time.Instant
import java.util.UUID


data class Rental(
    val title: String,
    val rentalId: UUID,
    val author: Account,
    val isNegotiable: Boolean,
    val description: String,
    val location: Location,
    val price: Int,
    val deposit: Int,
    val rooms: Int,
    val floor: Int,
    val size: Double,
    val buildYear: Int,
    val rentDate: Instant,
//    val featureTags: List<String>,
//    val photoUrls: List<String>,
) {

    companion object {
        fun fromDb(entity: RentalRepository.IRentalEntity): Rental {
            return entity.let {
                Rental(
                    it.title,
                    it.rentalId!!,
                    Account.fromDb(it.authorEntity),
                    it.isNegotiable,
                    it.description,
                    Location.fromDb(it.locationEntity),
                    it.price,
                    it.deposit,
                    it.rooms,
                    it.floor,
                    it.size,
                    it.buildYear,
                    it.rentDate
                )
            }
        }
    }
}
