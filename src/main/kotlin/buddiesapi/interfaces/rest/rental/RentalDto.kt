package buddiesapi.interfaces.rest.rental

import buddiesapi.domain.account.Account
import buddiesapi.domain.common.Location
import buddiesapi.domain.rental.Rental
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import java.time.Instant
import java.util.UUID
import org.springframework.format.annotation.DateTimeFormat

data class RentalDto(
    @Schema(example = "ff291c13-bd30-4950-a887-f0ab54c9eaf8", name = "Rental identification")
    val rentalId: UUID,

    @Schema(example = "ff291c13-bd30-4950-a887-f0ab54c9eaf8", name = "Author identification")
    val authorId: UUID,

    @Schema(example = "2 pokoje Warszawa Mokotów (GARAŻ)", name = "Title", required = true)
    val title: String?,

    @Schema(example = "false", name = "Is the price negotiable", required = true)
    val isNegotiable: Boolean?,

    @Schema(
        example = "Mam na sprzedaż świeżo wyremontowane mieszkanie (...) ",
        name = "Detailed description",
        required = true,
        maxLength = 3000
    )
    val description: @Size(max = 3000) String?,

    @Schema(example = "Warsaw, Poland", name = "textual location representation", required = true)
    val locationStr: String?,

    @Schema(example = "21.017532", name = "Longitude", required = true)
    val locationLng: Double?,

    @Schema(example = "52.237049", name = "Latitude", required = true)
    val locationLat: Double?,

    @Schema(example = "2999", name = "Total price", required = true)
    val price: Int?,

    @Schema(example = "3000", name = "Deposit", required = true)
    val deposit: Int?,

    @Schema(example = "2", name = "Room count", required = true)
    val rooms: Int?,

    @Schema(example = "5", name = "Floor the apartment is on", required = true)
    val floor: Int?,

    @Schema(example = "48.45", name = "Apartment size", required = true)
    val size: Double?,

//    @Schema(example = "110.14", name = "Price/size", required = true)
//     double pricePerM;
    @Schema(example = "2009", name = "How old the building is", required = true)
    val buildYear: Int?,

    @Schema(example = "2023-10-31T00:00:00.000-05:00", name = "Date when it will be available from", required = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val rentDate: Instant?

    //    @Schema(example = "['AC','Garage']", name = "Additional fatures of the rental property", required = true)
    //    @NonNull
    //     Set<String> featureTags;
    //
    //    @Schema(example = "[https://pjatk-s16604-diploma-media.s3.eu-central-1.amazonaws.com/logotype.png]", name = "Set of urls with the uploaded pictures", required = true)
    //    @NonNull
    //     Set<String> photoUrls;
) {


    companion object {

        fun from(domain: Rental): RentalDto =
            domain.let {
                RentalDto(
                    it.rentalId,
                    it.author.accountId,
                    it.title,
                    it.isNegotiable,
                    it.description,
                    it.location.readableText,
                    it.location.longitude,
                    it.location.latitude,
                    it.price,
                    it.deposit,
                    it.rooms,
                    it.floor,
                    it.size,
                    it.buildYear,
                    it.rentDate
                )
            }

        fun toDomain(dto: RentalDto, account: Account): Rental = dto.let {
            Rental(
                it.title!!,
                it.rentalId,
                account,
                it.isNegotiable!!,
                it.description!!,
                Location(null, it.locationStr!!, it.locationLng!!, it.locationLat!!),
                it.price!!,
                it.deposit!!,
                it.rooms!!,
                it.floor!!,
                it.size!!,
                it.buildYear!!,
                it.rentDate!!
            )
        }
    }
}
