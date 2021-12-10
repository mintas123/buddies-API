package pl.mroz.buddiesapi.domain.rental;

public class RentalServiceFactory {

    public RentalService rentalService(RentalRepository repository) {
        return new RentalServiceImpl(repository);
    }
}
