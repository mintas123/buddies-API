package pl.mroz.buddiesapi.infrastructure.database.rental;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mroz.buddiesapi.domain.rental.RentalRepository;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalEntity implements RentalRepository.IRentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID rentalId;

    //    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    //    @JoinColumn(name = "creator_id")
    //    private Account creator;

    @Basic
    //    @NotBlank
    //    @Size(max = 100)
    private String title;

    @Basic
    private boolean isNegotiable;

    @Basic
    //    @NotBlank
    //    @Size(max = 3000)
    private String description;

    @Basic
    private String locationStr;

    @Basic
    private double locationLng;

    @Basic
    private double locationLat;

    //  -----details:-----

    @Basic
    private int price;

    @Basic
    private int deposit;

    @Basic
    private int rooms;

    @Basic
    private int floor;

    @Basic
    private int size;

    @Basic
    @Column(name = "price_m_sq")
    private int pricePerM;

    @Basic
    private int buildYear;

    @Basic
    private LocalDate rentDate;

    @ElementCollection
    @CollectionTable(name = "feature_tags", joinColumns = @JoinColumn(name = "rental_id"))
    private Set<String> featureTags = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "rental_pic_urls", joinColumns = @JoinColumn(name = "rental_id"))
    private Set<String> photoUrls = new HashSet<>();

    // -----------------------

    //    @JsonIgnore
    //    @ManyToMany(fetch = FetchType.EAGER,
    //            mappedBy = "favoriteRentals"
    //    )
    //    private Set<Account> accountFavoriteSet = new HashSet<>();
}
