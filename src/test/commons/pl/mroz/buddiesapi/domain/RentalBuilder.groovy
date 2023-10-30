package pl.mroz.buddiesapi.domain

import com.github.javafaker.Faker
import pl.mroz.buddiesapi.domain.common.Location
import pl.mroz.buddiesapi.domain.generation.RandomBuilder
import pl.mroz.buddiesapi.domain.rental.Rental

import java.time.Instant
import java.time.LocalDate
import java.util.stream.IntStream

class RentalBuilder implements RandomBuilder {

    private final static Faker faker = new Faker(Locale.ENGLISH)

    //default values
    private final UUID rentalId = randomUUID
    private String title = faker.lorem().sentence(5, 5)
    private boolean isNegotiable = randomBoolean
    private String description = faker.lordOfTheRings().location().take(getRandomInt(500, 3000))
    private String locationStr = randomBoolean
    private double locationLng = getRandomDouble(0, 180.0)
    private double locationLat = getRandomDouble(0, 90.0)
    private int price = getRandomInt(500, 10_000)
    private int deposit = getRandomInt(500, 10_000)
    private int rooms = getRandomInt(1, 10)
    private int floor = getRandomInt(0, 50)
    private double size = getRandomInt(10, 200)
    private double pricePerM = (price / size)
    private int buildYear = getRandomInt(1900, LocalDate.now().getYear() + 5)
    private Instant rentDate = randomDate
    private Set<String> featureTags = faker.lorem().sentence(getRandomInt(2, 10)).split(" ").toList().toSet()
    private Set<String> photoUrls


    static Set<String> buildPhotoUrls() {
        Set<String> set = new HashSet<>()
        IntStream.range(0, 10).forEach(i -> set.add(faker.internet().url()))
//        doNTimes(10, () -> set.add(faker.internet().url()))
        return set
    }

    RentalBuilder withIsNegotiable(boolean isNegotiable) {
        this.isNegotiable = isNegotiable
        return this
    }

    RentalBuilder withPrice(int price) {
        this.price = price
        this.pricePerM = this.price / this.size
        return this
    }

    RentalBuilder withRentDate(LocalDate date) {
        this.rentDate = date
        return this
    }

    RentalBuilder withFeatureSet(Set<String> features) {
        this.featureTags = features
        return this
    }

    RentalBuilder withPhotoUrls(int amount) {
        Set<String> set = new HashSet<>()
        (0..amount).forEach(i -> set.add(faker.internet().url()))

        this.photoUrls = set
        return this
    }

    Rental build() {
        def acc = new AccountBuilder().build()
        def loc = new Location(randomUUID, locationStr, locationLng, locationLat)
        return new Rental(title, rentalId, acc, isNegotiable, description, loc, price,
                deposit, rooms, floor, size, buildYear, rentDate)
    }
}
