package buddiesapi.domain.rental

import buddiesapi.domain.RentalBuilder
import buddiesapi.domain.generation.RandomBuilder
import buddiesapi.infrastructure.database.rental.RentalRepositoryInMemory
import spock.lang.Specification

class RentalDomainServiceImplUT extends Specification implements RandomBuilder {
    RentalRepository repository = new RentalRepositoryInMemory()
    RentalDomainService service = RentalDomainServiceFactory.rentalDomainService(repository)


    def 'createRental should persist new Rental'() {
        given:
            def rental = new RentalBuilder().build()
        when:
            service.createRental(rental)
        then:
            def persisted = repository.getRentalById(rental.getRentalId())
            persisted.rentalId == rental.rentalId
            persisted.description == rental.description
            persisted.size == rental.size
            persisted.title == rental.title
    }

    def 'updateRental should persist new data about Rental'() {
        given:
            def uuid = UUID.randomUUID()
            def rental = new RentalBuilder()
                    .withRentalId(uuid)
                    .withPrice(100)
                    .build()
            service.createRental(rental)
        when:
            def rentalUpdate = new RentalBuilder()
                    .withRentalId(uuid)
                    .withPrice(1500)
                    .build()
            service.updateRental(uuid, rentalUpdate)
        then:
            def persisted = repository.getRentalById(rental.getRentalId())
            persisted.price == 1500
            persisted.price != 100
    }

    def 'deleteRental should persist removing data about Rental'() {
        given:
            def rental = new RentalBuilder().build()
            service.createRental(rental)
        when:
            service.deleteRental(rental)
        and:
            repository.getRentalById(rental.getRentalId())
        then:
            thrown(IllegalArgumentException)
    }
}
