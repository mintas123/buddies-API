package buddiesapi.domain.rental

import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface RentalDomainService {
    fun getAllRentals(): List<Rental>

    fun getByCriteria(rentalCriteria: RentalRepository.IRentalCriteria, pageable: Pageable): Page<Rental>

    fun getRentalsFromUser(accountId: UUID): List<Rental>

    fun getRental(rentalId: UUID): Rental

    fun getTopTags(): List<String>

    fun createRental(rental: Rental): Rental

    fun updateRental(uuid: UUID, rental: Rental): Rental

    fun deleteRental(rental: Rental)
}
