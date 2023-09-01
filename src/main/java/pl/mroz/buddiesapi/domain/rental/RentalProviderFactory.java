package pl.mroz.buddiesapi.domain.rental;

public class RentalProviderFactory {

    public static RentalProvider rentalProvider(RentalRepository repository) {
        return new RentalProviderImpl(repository);
    }
}
