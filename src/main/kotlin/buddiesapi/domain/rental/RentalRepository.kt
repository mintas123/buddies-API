package buddiesapi.domain.rental

import buddiesapi.domain.common.Location
import buddiesapi.infrastructure.database.account.AccountEntity
import buddiesapi.infrastructure.database.location.LocationEntity
import buddiesapi.infrastructure.database.rental.RentalEntity
import java.time.Instant
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface RentalRepository {
    fun getAllRentals(): List<Rental>

    fun getByCriteria(criteria: Specification<RentalEntity>, pageable: Pageable): Page<Rental>

    fun getRentalById(rentalId: UUID): Rental?

    fun getAllRentalsFromUser(accountId: UUID): List<Rental>

    val topTags: List<String>

    fun save(rental: Rental): Rental

    fun delete(rental: Rental)

    interface IRentalEntity {
        val rentalId: UUID?

        val authorEntity: AccountEntity

        val title: String

        val isNegotiable: Boolean

        val description: String

        val locationEntity: LocationEntity

        val price: Int

        val deposit: Int

        val rooms: Int

        val floor: Int

        val size: Double

        //        double getPricePerM();
        val buildYear: Int

        //        Set<String> getFeatureTags();
        val rentDate: Instant

        //        Set<String> getPhotoUrls();
    }

    interface IRentalCriteria {
        val maxDistance: Int?

        val location: Location?

        val priceMin: Int?

        val priceMax: Int?

        val sizeMin: Int?

        val sizeMax: Int?

        val roomsMin: Int?

        val roomsMax: Int?

        val buildMin: Int?

        val buildMax: Int?

        val moveMin: Int?

        val moveMax: Int?

        val features: Set<String?>?
    }
}
