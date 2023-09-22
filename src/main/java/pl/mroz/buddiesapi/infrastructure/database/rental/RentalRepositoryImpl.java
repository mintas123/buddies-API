package pl.mroz.buddiesapi.infrastructure.database.rental;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mroz.buddiesapi.domain.rental.Rental;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentalRepository {

    private final RentalJpaRepository jpaRepository;

    @Override
    public List<Rental> getAllRentals() {
        return jpaRepository.findAll()
                .stream()
                .map(Rental::fromDb)
                .toList();
    }

    @Override
    public Rental getRentalById(UUID rentalId) {
        return jpaRepository.findById(rentalId)
                .map(Rental::fromDb)
                .orElseThrow(
                        () -> new IllegalArgumentException("No Rental with UUID: " + rentalId)
                );
    }

    @Override
    public List<String> getTopTags() {
        throw new NotImplementedException();
//        return jpaRepository.getTopTags();
    }

    @Override
    public Rental save(Rental rental) {
        jpaRepository.findById(rental.getRentalId())
                .ifPresent(rentalEntity -> {
                    throw new IllegalStateException("Cannot save, rental with " + rental.getRentalId() + " already exists");
                });
        jpaRepository.save(RentalEntity.fromDomain(rental));
        return rental;
    }

    @Override
    public Rental update(Rental rental) {
        var rentalEntity = jpaRepository.getReferenceById(rental.getRentalId());
        rentalEntity.setTitle(rental.getTitle());
        rentalEntity.setNegotiable(rental.isNegotiable());
        rentalEntity.setDescription(rental.getDescription());
        rentalEntity.setLocationStr(rental.getLocationStr());
        rentalEntity.setLocationLat(rental.getLocationLat());
        rentalEntity.setLocationLng(rental.getLocationLng());
        rentalEntity.setPrice(rental.getPrice());
        rentalEntity.setDeposit(rental.getDeposit());
        rentalEntity.setRooms(rental.getRooms());
        rentalEntity.setFloor(rental.getFloor());
        rentalEntity.setSize(rental.getSize());
        rentalEntity.setBuildYear(rental.getBuildYear());
        rentalEntity.setRentDate(rental.getRentDate());
//        rentalEntity.setFeatureTags(rental.getFeatureTags());
//        rentalEntity.setPhotoUrls(rental.getPhotoUrls());

        jpaRepository.save(rentalEntity);

        return rental;
    }

    @Override
    public void delete(Rental rental) {
        jpaRepository.findById(rental.getRentalId())
                .ifPresent(jpaRepository::delete);
    }

    interface RentalJpaRepository extends JpaRepository<RentalEntity, UUID> {

//        @Query(value = "SELECT feature_tags FROM public.feature_tags Group By feature_tags Order By COUNT(*) DESC LIMIT 5",
//                nativeQuery = true)
//        List<String> getTopTags();

    }
}
