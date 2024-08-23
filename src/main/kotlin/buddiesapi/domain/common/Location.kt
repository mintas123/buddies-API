package buddiesapi.domain.common

import buddiesapi.infrastructure.database.location.LocationEntity
import java.util.UUID


class Location(
    val locationId: UUID?,
    val readableText: String,
    val longitude: Double,
    val latitude: Double
) {
    companion object {
        fun fromDb(db: LocationEntity): Location {
            return Location(db.locationId!!, db.readableText, db.longitude, db.latitude)
        }
    }
}
