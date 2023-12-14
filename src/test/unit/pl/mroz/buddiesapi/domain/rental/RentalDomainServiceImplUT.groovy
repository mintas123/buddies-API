package pl.mroz.buddiesapi.domain.rental

import pl.mroz.buddiesapi.domain.RentalBuilder
import pl.mroz.buddiesapi.domain.generation.RandomBuilder
import pl.mroz.buddiesapi.infrastructure.database.rental.RentalRepositoryInMemory
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
        def rental = new RentalBuilder().withPrice(100).build()
        service.createRental(rental)
        when:
        rental.price = 1500
        service.updateRental(rental.getRentalId(), rental)
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
