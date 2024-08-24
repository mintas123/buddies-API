package buddiesapi.domain.rental

import buddiesapi.domain.common.ObjectMapper
import buddiesapi.infrastructure.database.rental.RentalEntity
import java.util.UUID
import mu.KLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification


class RentalDomainServiceImpl(val repository: RentalRepository) : RentalDomainService {

    override val allRentals: List<Rental> = repository.getAllRentals()


    override fun getByCriteria(rentalCriteria: RentalRepository.IRentalCriteria, pageable: Pageable): Page<Rental> {
        val spec = Specification.where<RentalEntity>(null)
        //todo https://medium.com/@bubu.tripathy/dynamic-query-with-specification-interface-in-spring-data-jpa-ae8764e32162
        return repository.getByCriteria(spec, pageable)
    }

    override fun getRentalsFromUser(accountId: UUID): List<Rental> = repository.getAllRentalsFromUser(accountId)


    override fun getRental(rentalId: UUID): Rental {
        repository.getRentalById(rentalId)?.let { return it }
        logger().error { "could not get Rental from repository UUID: $rentalId" }
        throw RuntimeException("could not get Rental from repository UUID: $rentalId")
    }

    override fun getTopTags(): List<String> = repository.topTags


    override fun createRental(rental: Rental): Rental = repository.save(rental)


    override fun updateRental(uuid: UUID, rental: Rental): Rental =
        repository.save(ObjectMapper.mapInto(rental, getRental(uuid)))


    override fun deleteRental(rental: Rental) {
        logger().info { "Rental deleted. UUID: ${rental.rentalId}" }
        // todo remove files from the cloud when ready
        repository.delete(rental)
    }

    companion object : KLogging()
}
