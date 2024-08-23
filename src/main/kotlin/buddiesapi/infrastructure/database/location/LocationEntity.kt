package buddiesapi.infrastructure.database.location


import buddiesapi.domain.common.Location
import jakarta.persistence.Basic
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "LOCATION")
data class LocationEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var locationId: UUID? = null,
    @Basic
    var readableText: String,
    @Basic
    var longitude: Double,
    @Basic
    var latitude: Double,
) {

    companion object {
        fun fromDomain(domain: Location): LocationEntity =
            domain.let {
                LocationEntity(it.locationId, it.readableText, it.longitude, it.latitude)
            }
    }
}
