package pl.mroz.buddiesapi.infrastructure.database.rental;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mroz.buddiesapi.domain.rental.Rental;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;
import pl.mroz.buddiesapi.infrastructure.database.location.LocationEntity;

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
    public Page<Rental> getByCriteria(Specification<RentalEntity> specification, Pageable pageable) {
        return jpaRepository.findAll(specification, pageable).map(Rental::fromDb);
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
    public List<Rental> getAllRentalsFromUser(UUID accountId) {
        return jpaRepository.getAllByAuthorEntityId(accountId)
                .stream()
                .map(Rental::fromDb)
                .toList();
    }

    @Override
    public List<String> getTopTags() {
        throw new NotImplementedException();
//        return jpaRepository.getTopTags();
    }

    @Override
    public Rental save(Rental rental) {
        if (rental.getRentalId() != null) {
            jpaRepository.findById(rental.getRentalId())
                    .ifPresent(rentalEntity -> {
                        throw new IllegalStateException("Cannot save, rental with " + rental.getRentalId() + " already exists");
                    });
        }
        var savedEntity = jpaRepository.save(RentalEntity.fromDomain(rental));
        return Rental.fromDb(savedEntity);
    }

    @Override
    public Rental update(Rental rental) {
        var rentalEntity = jpaRepository.getReferenceById(rental.getRentalId());
        rentalEntity.setTitle(rental.getTitle());
        rentalEntity.setNegotiable(rental.isNegotiable());
        rentalEntity.setDescription(rental.getDescription());
        rentalEntity.setLocationEntity(LocationEntity.fromDomain(rental.getLocation()));
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

    interface RentalJpaRepository extends JpaRepository<RentalEntity, UUID>, JpaSpecificationExecutor<RentalEntity> {

//        @Query(value = "SELECT feature_tags FROM public.feature_tags Group By feature_tags Order By COUNT(*) DESC LIMIT 5",
//                nativeQuery = true)
//        List<String> getTopTags();

        @Query(value = "SELECT r FROM RentalEntity r where r.authorEntity.accountId = :authorId")
        List<RentalEntity> getAllByAuthorEntityId(@Param("authorId") UUID authorId);

    }
}
