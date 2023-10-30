package pl.mroz.buddiesapi.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mroz.buddiesapi.domain.common.Location;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private UUID accountId;
    private String email;
    private String hashedPassword;
    private String name;
    private String lastName;
    private Location location;
    // todo rethink domain scopes and db schema

    public static Account fromDb(AccountRepository.IAccountEntity entity) {
        return Account.builder()
                .accountId(entity.getAccountId())
                .email(entity.getEmail())
                .hashedPassword(entity.getHashedPassword())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .location(Location.fromDb(entity.getLocationEntity()))
                .build();
    }
}
