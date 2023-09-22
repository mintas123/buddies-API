package pl.mroz.buddiesapi.infrastructure.database.account;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mroz.buddiesapi.domain.account.AccountRepository;

import java.util.UUID;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountEntity implements AccountRepository.IAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountId;

    @Basic
    @Email(message = "Invalid email")
    private String email;

    @Basic
    @NotBlank
    private String hashedPassword;

    @Basic
    @NotBlank
    private String name;

    @Basic
    @NotBlank
    private String lastName;
}
