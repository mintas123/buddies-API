package pl.mroz.buddiesapi.infrastructure.database.account;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mroz.buddiesapi.domain.account.Account;
import pl.mroz.buddiesapi.domain.account.AccountRepository;
import pl.mroz.buddiesapi.infrastructure.database.location.LocationEntity;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository jpaRepository;

    private final LocationJpaRepository locationJpaRepository;

    @Override
    public List<Account> findAll() {
        return jpaRepository.findAll().stream()
                .map(Account::fromDb)
                .toList();
    }

    @Override
    public Account save(Account account) {
        if (account.getAccountId() != null) {
            jpaRepository.findById(account.getAccountId())
                    .ifPresent(accountEntity -> {
                        throw new IllegalStateException("Cannot save, account with "
                                + account.getAccountId() + " already exists");
                    });
        }
        jpaRepository.save(AccountEntity.fromDomain(account));
        return account;
    }

    interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    }

    interface LocationJpaRepository extends JpaRepository<LocationEntity, UUID> {

    }
}
