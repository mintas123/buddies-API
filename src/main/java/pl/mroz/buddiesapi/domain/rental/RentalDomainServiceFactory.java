package pl.mroz.buddiesapi.domain.rental;

public class RentalDomainServiceFactory {

    public static RentalDomainService rentalDomainService(RentalRepository repository) {
        return new RentalDomainServiceImpl(repository);
    }
}
