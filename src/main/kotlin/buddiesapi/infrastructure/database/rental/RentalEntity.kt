package buddiesapi.infrastructure.database.rental

import buddiesapi.domain.rental.Rental
import buddiesapi.domain.rental.RentalRepository
import buddiesapi.infrastructure.database.account.AccountEntity
import buddiesapi.infrastructure.database.location.LocationEntity
import jakarta.persistence.Basic
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "RENTAL")
data class RentalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override val rentalId: UUID? = null,

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "author_id")
    override val authorEntity: AccountEntity,

    @Basic
    //    @NotBlank
    //    @Size(max = 100)
    override val title: String,

    @Basic
    override val isNegotiable: Boolean,

    @Basic
    //    @NotBlank
    //    @Size(max = 3000)
    override val description: String,

    @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "location_id")
    override val locationEntity: LocationEntity,

//  -----details:-----
    @Basic
    override val price: Int,

    @Basic
    override val deposit: Int,

    @Basic
    override val rooms: Int,

    @Basic
    override val floor: Int,

    @Basic
    override val size: Double,

    //  @Basic
    //  @Column(name = "price_m_sq")
    //  private double pricePerM;
    @Basic
    override val buildYear: Int,

    @Basic
    override val rentDate: Instant,

    //    @ElementCollection
    //    @CollectionTable(name = "feature_tags", joinColumns = @JoinColumn(name = "rental_id"))
    //    private Set<String> featureTags;
    //
    //    @ElementCollection
    //    @CollectionTable(name = "rental_pic_urls", joinColumns = @JoinColumn(name = "rental_id"))
    //    private Set<String> photoUrls;
    // -----------------------
    //    @JsonIgnore
    //    @ManyToMany(fetch = FetchType.EAGER,
    //            mappedBy = "favoriteRentals"
    //    )
    //    private Set<Account> accountFavoriteSet = new HashSet<>();

) : RentalRepository.IRentalEntity {
    
    companion object {
        @JvmStatic
        fun fromDomain(domain: Rental): RentalEntity =
            domain.let {
                RentalEntity(
                    it.rentalId,
                    AccountEntity.fromDomain(it.author),
                    it.title,
                    it.isNegotiable,
                    it.description,
                    LocationEntity.fromDomain(it.location),
                    it.price,
                    it.deposit,
                    it.rooms,
                    it.floor,
                    it.size,
                    it.buildYear,
                    it.rentDate,
                )
            }
    }
}
