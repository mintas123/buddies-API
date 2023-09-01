package pl.mroz.buddiesapi.domain.rental

import pl.mroz.buddiesapi.domain.RentalBuilder
import pl.mroz.buddiesapi.domain.generation.RandomBuilder
import pl.mroz.buddiesapi.infrastructure.database.rental.RentalRepositoryInMemory
import spock.lang.Specification

class RentalProviderImplUT extends Specification implements RandomBuilder {
    RentalRepository repository = new RentalRepositoryInMemory()
    RentalProvider service = RentalProviderFactory.rentalProvider(repository)


    def 'createRental should persist new Rental'() {
        given:
        def rental = new RentalBuilder().build()
        when:
        def _ = service.createRental(rental)
        then:
        def persisted = repository.getRentalById(rental.getRentalId())
        persisted.rentalId == rental.rentalId
        persisted.description == rental.description
        persisted.size == rental.size
        persisted.title == rental.title
    }

    def 'createRental should throw exception when rental with same UUID already exists'() {
        given:
        def rental = new RentalBuilder().build()
        when:
        def _ = service.createRental(rental)
        and:
        def _2 = service.createRental(rental)
        then:
        thrown(IllegalStateException)
    }

    def 'updateRental should persist new data about Rental'() {
        given:
        def rental = new RentalBuilder().withPrice(100).build()
        def _ = service.createRental(rental)
        when:
        rental.price = 1500
        def _2 = service.updateRental(rental)
        then:
        def persisted = repository.getRentalById(rental.getRentalId())
        persisted.price == 1500
        persisted.price != 100
    }

    def 'updateRental should throw exception when rental doesnt exists'() {
        given:
        def rental = new RentalBuilder().build()
        when:
        def _ = service.updateRental(rental)
        then:
        thrown(IllegalArgumentException)
    }

    def 'deleteRental should persist removing data about Rental'() {
        given:
        def rental = new RentalBuilder().build()
        def _ = service.createRental(rental)
        when:
        service.deleteRental(rental)
        and:
        def _2 = repository.getRentalById(rental.getRentalId())
        then:
        thrown(IllegalArgumentException)
    }
}
