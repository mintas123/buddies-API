package pl.mroz.buddiesapi.infrastructure.database.account;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mroz.buddiesapi.domain.account.Account;
import pl.mroz.buddiesapi.domain.account.AccountRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository jpaRepository;

    @Override
    public List<Account> findAll() {
        return jpaRepository.findAll().stream()
                .map(Account::fromDb)
                .toList();
    }

    @Override
    public Account getById(UUID accountUUID) {
        return Account.fromDb(jpaRepository.getByAccountId(accountUUID));
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
        var savedEntity = jpaRepository.save(AccountEntity.fromDomain(account));
        return Account.fromDb(savedEntity);
    }

    interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {

        AccountEntity getByAccountId(UUID accountId);
    }
}
