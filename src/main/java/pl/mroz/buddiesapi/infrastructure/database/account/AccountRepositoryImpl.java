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
        var savedEntity = jpaRepository.save(AccountEntity.fromDomain(account));
        return Account.fromDb(savedEntity);
    }

    @Override
    public void delete(Account account) {
        jpaRepository.delete(AccountEntity.fromDomain(account));
    }

    interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {

        AccountEntity getByAccountId(UUID accountId);
    }
}
