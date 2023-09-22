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

    interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    }
}
