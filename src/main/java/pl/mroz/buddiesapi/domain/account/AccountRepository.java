package pl.mroz.buddiesapi.domain.account;

import pl.mroz.buddiesapi.infrastructure.database.location.LocationEntity;

import java.util.List;
import java.util.UUID;

public interface AccountRepository {

    List<Account> findAll();

    Account save(Account account);

    interface IAccountEntity {
        UUID getAccountId();

        String getEmail();

        String getHashedPassword();

        String getName();

        String getLastName();

        LocationEntity getLocationEntity();

    }
}
