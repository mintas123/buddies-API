package pl.mroz.buddiesapi.domain.rental;

public class RentalProviderFactory {

    public RentalProvider rentalService(RentalRepository repository) {
        return new RentalProviderImpl(repository);
    }
}
