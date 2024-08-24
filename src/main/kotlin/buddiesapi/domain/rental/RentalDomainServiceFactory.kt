package buddiesapi.domain.rental

object RentalDomainServiceFactory {
    @JvmStatic
    fun rentalDomainService(repository: RentalRepository): RentalDomainService {
        return RentalDomainServiceImpl(repository)
    }
}
