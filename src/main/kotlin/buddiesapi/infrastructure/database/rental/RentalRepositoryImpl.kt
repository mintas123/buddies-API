package buddiesapi.infrastructure.database.rental

import buddiesapi.domain.rental.Rental
import buddiesapi.domain.rental.RentalRepository
import buddiesapi.infrastructure.database.rental.RentalEntity.Companion.fromDomain
import java.util.UUID
import mu.KLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

class RentalRepositoryImpl(
    private val jpaRepository: RentalJpaRepository
) : RentalRepository {

    override fun getAllRentals(): List<Rental> = jpaRepository.findAll().stream()
        .map { entity: RentalEntity? -> entity?.let { Rental.fromDb(it) } }
        .toList()

    override fun getByCriteria(criteria: Specification<RentalEntity>, pageable: Pageable): Page<Rental> =
        jpaRepository.findAll(criteria, pageable)
            .map { entity: RentalEntity? ->
                entity?.let { Rental.fromDb(it) }
            }

    override fun getRentalById(rentalId: UUID): Rental? =
        jpaRepository.getByRentalId(rentalId)?.let {
            Rental.fromDb(it)
        }

    override fun getAllRentalsFromUser(accountId: UUID): List<Rental> =
        jpaRepository.getAllByAuthorEntityId(accountId)
            .stream()
            .map { entity: RentalEntity? ->
                entity?.let { Rental.fromDb(it) }
            }
            .toList()


    override val topTags: List<String> = emptyList() //todo

    override fun save(rental: Rental): Rental {
        logger.info { "Saving ${rental.rentalId}, with userID: ${rental.author.accountId}" }
        val savedEntity = jpaRepository.save(fromDomain(rental))
        return Rental.fromDb(savedEntity)
    }

    override fun delete(rental: Rental) =
        jpaRepository.delete(fromDomain(rental))


    companion object : KLogging()

    interface RentalJpaRepository : JpaRepository<RentalEntity?, UUID>,
        JpaSpecificationExecutor<RentalEntity> {
        //        @Query(value = "SELECT feature_tags FROM public.feature_tags Group By feature_tags Order By COUNT(*) DESC LIMIT 5",
        //                nativeQuery = true)
        //        List<String> getTopTags();
        @Query(value = "SELECT r FROM RentalEntity r where r.authorEntity.accountId = :authorId")
        fun getAllByAuthorEntityId(@Param("authorId") authorId: UUID): List<RentalEntity>

        fun getByRentalId(accountId: UUID): RentalEntity?
    }
}
