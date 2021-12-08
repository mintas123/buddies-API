package pl.mroz.buddiesapi.domain.rental;

public interface RentalRepository {

    int someMethod();
    // getAll, save, update,delete and all other

    interface IRentalEntity {
        String getTitle();
    }
}
